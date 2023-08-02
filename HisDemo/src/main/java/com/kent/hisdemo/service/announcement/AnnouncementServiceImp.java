package com.kent.hisdemo.service.announcement;


import com.kent.hisdemo.dao.read.measure.MeasureReadMapper;
import com.kent.hisdemo.dao.write.measure.MeasureWriteMapper;
import com.kent.hisdemo.entity.announcement.Announcement;
import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.vo.param.measure.MeasureParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("measureService")
public class AnnouncementServiceImp implements AnnouncementService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MeasureWriteMapper measureWriteMapper;
    @Autowired
    MeasureReadMapper measureReadMapper;


    @Override
    public List<Announcement> getMeasureHistoryDataByUser(User user) {
        return null;
    }
}
