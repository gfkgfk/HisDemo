package com.kent.hisdemo.controller.measure;


import com.kent.hisdemo.common.response.JsonResult;
import com.kent.hisdemo.common.response.SldResponse;
import com.kent.hisdemo.config.annotation.CurrentUser;
import com.kent.hisdemo.dto.measure.MeasureDetailDTO;
import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.service.feedback.FeedbackService;
import com.kent.hisdemo.service.measure.MeasureService;
import com.kent.hisdemo.vo.param.measure.MeasureParam;
import com.kent.hisdemo.vo.vo.measure.MeasureDetailVO;
import com.kent.hisdemo.vo.vo.measure.MeasureVO;
import io.swagger.annotations.Api;
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
@Api(value = "v1", tags = "2.HisDemo测量数据采集功能接口")
@RequestMapping("/api/v1/measure")
public class MeasureController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MeasureService measureService;

    @Autowired
    FeedbackService feedbackService;


    @RequestMapping(value = "/uploadMeasureData", method = RequestMethod.POST)
    public JsonResult uploadMeasureData(HttpServletRequest request, MeasureParam param, @CurrentUser User user) {
//        User user = (User) request.getAttribute("user");
        measureService.uploadMeasureData(param, user.getId());
        return SldResponse.success("操作成功");
    }

    @RequestMapping(value = "/getMeasureHistory", method = RequestMethod.POST)
    public JsonResult<List<MeasureVO>> getMeasureHistoryData(HttpServletRequest request, MeasureParam param, @CurrentUser User user) {
//        User user = (User) request.getAttribute("user");
        List<Measure> list = measureService.getMeasureHistoryDataByUser(user);
        List<MeasureVO> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MeasureVO measureVO = new MeasureVO();
            measureVO.setMeasureValue(list.get(i).getValue());
            measureVO.setDeviceType(list.get(i).getType());
            result.add(measureVO);
        }
        System.out.println(result);
        return SldResponse.success(result);
    }

    @RequestMapping(value = "/getMeasureDetailsList", method = RequestMethod.POST)
    public JsonResult<List<MeasureDetailVO>> getMeasureDetailsList(HttpServletRequest request, MeasureParam param, @CurrentUser User user) {
        List<MeasureDetailDTO> list = measureService.getMeasureDetailsList();
        List<MeasureDetailVO> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            MeasureDetailVO measureDetailVO = new MeasureDetailVO();
            measureDetailVO.setId(list.get(i).getId());
            measureDetailVO.setUserName(list.get(i).getUserName());
            measureDetailVO.setDeviceType(list.get(i).getDeviceType());
            measureDetailVO.setMeasureValue(list.get(i).getMeasureValue());
            measureDetailVO.setCreateTime(list.get(i).getCreateTime());
            result.add(measureDetailVO);
        }
        return SldResponse.success(result);
    }

    @RequestMapping(value = "/deleteMeasureDetailById", method = RequestMethod.POST)
    public JsonResult deleteMeasureDetailById(HttpServletRequest request, Long id, @CurrentUser User user) {
        Measure measure = measureService.getMeasureDetailById(id);
        if (null != measure) {
            measureService.deleteMeasureDetailById(id);
            if (null != measure.getFeedbackId() && measure.getFeedbackId() > 0) {
                feedbackService.deleteFeedbackById(measure.getFeedbackId());
            }
        }
        return SldResponse.success("操作成功");
    }

}
