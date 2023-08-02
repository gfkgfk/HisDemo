package com.kent.hisdemo.controller.community;

import com.kent.hisdemo.common.response.JsonResult;
import com.kent.hisdemo.common.response.SldResponse;
import com.kent.hisdemo.config.annotation.CurrentUser;
import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.service.measure.MeasureService;
import com.kent.hisdemo.vo.param.measure.MeasureParam;
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
@RequestMapping("/api/v1/community")
public class CommunityController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MeasureService measureService;


    @RequestMapping(value = "/getAllAnnouncement", method = RequestMethod.POST)
    public JsonResult getAllAnnouncement(HttpServletRequest request, @CurrentUser User user) {
        return SldResponse.success("操作成功");
    }

}