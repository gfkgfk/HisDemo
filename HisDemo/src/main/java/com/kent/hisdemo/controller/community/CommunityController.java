package com.kent.hisdemo.controller.community;

import com.kent.hisdemo.common.response.JsonResult;
import com.kent.hisdemo.common.response.SldResponse;
import com.kent.hisdemo.config.annotation.CurrentUser;
import com.kent.hisdemo.entity.announcement.Announcement;
import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.service.announcement.AnnouncementService;
import com.kent.hisdemo.service.measure.MeasureService;
import com.kent.hisdemo.vo.param.measure.MeasureParam;
import com.kent.hisdemo.vo.vo.community.AnnouncementVO;
import com.kent.hisdemo.vo.vo.measure.MeasureVO;
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
import java.util.Date;
import java.util.List;


@RestController
@Api(value = "v1", tags = "2.HisDemo测量数据采集功能接口")
@RequestMapping("/api/v1/community")
public class CommunityController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AnnouncementService announcementService;


    @RequestMapping(value = "/saveAnnouncement", method = RequestMethod.POST)
    public JsonResult saveAnnouncement(HttpServletRequest request, @CurrentUser User user,String title,String content) {
        Announcement announcement = new Announcement();
        announcement.setTitle(title);
        announcement.setContent(content);
        announcement.setCreateTime(new Date());
        announcementService.saveAnnouncement(announcement);
        return SldResponse.success("操作成功");
    }

    @RequestMapping(value = "/deleteAnnouncement", method = RequestMethod.POST)
    public JsonResult deleteAnnouncement(HttpServletRequest request, @CurrentUser User user,Long id) {
        announcementService.deleteAnnouncementById(id);
        return SldResponse.success("操作成功");
    }

    @RequestMapping(value = "/getAllAnnouncement", method = RequestMethod.POST)
    public JsonResult<List<AnnouncementVO>> getAllAnnouncement(HttpServletRequest request, @CurrentUser User user) {
        List<Announcement> list = announcementService.getAllAnnouncement(user);
        List<AnnouncementVO> vos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AnnouncementVO announcementVO = new AnnouncementVO();
            announcementVO.setId(list.get(i).getId());
            announcementVO.setTitle(list.get(i).getTitle());
            announcementVO.setContent(list.get(i).getContent());
            announcementVO.setCreateTime(list.get(i).getCreateTime());
            vos.add(announcementVO);
        }
        return SldResponse.success(vos);
    }


    @RequestMapping(value = "/getLatestAnnouncement", method = RequestMethod.POST)
    public JsonResult<AnnouncementVO> getLatestAnnouncement(HttpServletRequest request, @CurrentUser User user) {
        Announcement announcement = announcementService.getLatestAnnouncement(user);
        AnnouncementVO announcementVO = new AnnouncementVO();
        announcementVO.setId(announcement.getId());
        announcementVO.setTitle(announcement.getTitle());
        announcementVO.setContent(announcement.getContent());
        announcementVO.setCreateTime(announcement.getCreateTime());
        return SldResponse.success(announcementVO);
    }

    @RequestMapping(value = "/getAnnouncementById", method = RequestMethod.POST)
    @ApiOperation(value = "根据公告ID获取公告详情", notes = "返回公告详情")
    public JsonResult<AnnouncementVO> getLatestAnnouncement(HttpServletRequest request, @CurrentUser User user,Long id) {
        Announcement announcement = announcementService.getAnnouncementById(id);
        AnnouncementVO announcementVO = new AnnouncementVO();
        announcementVO.setId(announcement.getId());
        announcementVO.setTitle(announcement.getTitle());
        announcementVO.setContent(announcement.getContent());
        announcementVO.setCreateTime(announcement.getCreateTime());
        return SldResponse.success(announcementVO);
    }

}