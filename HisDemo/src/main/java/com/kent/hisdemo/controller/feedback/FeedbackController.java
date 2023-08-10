package com.kent.hisdemo.controller.feedback;

import com.kent.hisdemo.common.response.JsonResult;
import com.kent.hisdemo.common.response.SldResponse;
import com.kent.hisdemo.config.annotation.CurrentUser;
import com.kent.hisdemo.dto.feedback.FeedbackDTO;
import com.kent.hisdemo.entity.announcement.Announcement;
import com.kent.hisdemo.entity.feedback.Feedback;
import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.service.announcement.AnnouncementService;
import com.kent.hisdemo.service.feedback.FeedbackService;
import com.kent.hisdemo.service.measure.MeasureService;
import com.kent.hisdemo.utils.AssertUtil;
import com.kent.hisdemo.vo.vo.community.AnnouncementVO;
import com.kent.hisdemo.vo.vo.feedback.FeedbackDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@Api(value = "v1", tags = "5.HisDemo反馈功能接口")
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    MeasureService measureService;


    @RequestMapping(value = "/saveFeedback", method = RequestMethod.POST)
    public JsonResult saveFeedback(HttpServletRequest request, @CurrentUser User user) {
        return SldResponse.success("操作成功");
    }

    @RequestMapping(value = "/updateFeedback", method = RequestMethod.POST)
    public JsonResult updateFeedback(HttpServletRequest request, @CurrentUser User user, Long measureId, String content) {

        Measure measure = measureService.getMeasureDetailById(measureId);
        AssertUtil.notNull(measure,"测量信息不存在");

        if (null != measure.getFeedbackId() && measure.getFeedbackId()>0){//存在反馈信息则进行更新
            Feedback feedback = new Feedback();
            feedback.setId(measure.getFeedbackId());
            feedback.setUserId(user.getId());
            feedback.setContent(content);
            feedbackService.updateFeedback(feedback);
        }else {//没有则进行新增反馈信息
            Feedback feedback = new Feedback();
            feedback.setUserId(user.getId());
            feedback.setContent(content);
            feedbackService.saveFeedback(feedback);
            logger.debug(feedback.toString());
            measure.setFeedbackId(feedback.getId());
            measureService.updateMeasureDetail(measure);
        }
        return SldResponse.success("操作成功");
    }


    @RequestMapping(value = "/getAllFeedBack", method = RequestMethod.POST)
    public JsonResult<List<FeedbackDetailVO>> getAllFeedBack(HttpServletRequest request, @CurrentUser User user) {
        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedBackIncludeName();
        List<FeedbackDetailVO> list = new ArrayList<>();
        for (int i = 0; i < feedbackList.size(); i++) {
            FeedbackDetailVO feedbackDetailVO = new FeedbackDetailVO();
            feedbackDetailVO.setUserName(feedbackList.get(i).getUserName());
            feedbackDetailVO.setId(feedbackList.get(i).getId());
            feedbackDetailVO.setContent(feedbackList.get(i).getContent());
            list.add(feedbackDetailVO);
        }
        return SldResponse.success(list);
    }

    @RequestMapping(value = "/getFeedBackById", method = RequestMethod.POST)
    @ApiOperation(value = "反馈信息ID获取反馈信息", notes = "反馈信息ID获取反馈信息")
    public JsonResult<FeedbackDetailVO> getFeedBackById(HttpServletRequest request, @CurrentUser User user, Long measureId) {
        Measure measure =  measureService.getMeasureDetailById(measureId);
        Feedback feedback = feedbackService.getFeedbackById(measure.getFeedbackId());
        FeedbackDetailVO feedbackDetailVO = null;
        if (null != feedback) {
            feedbackDetailVO = new FeedbackDetailVO();
            feedbackDetailVO.setId(feedback.getId());
            feedbackDetailVO.setContent(feedback.getContent());
            feedbackDetailVO.setUserId(feedback.getUserId());
        }
        return SldResponse.success(feedbackDetailVO);
    }

}