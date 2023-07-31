package com.kent.hisdemo.service.measure;


import com.kent.hisdemo.dao.read.menu.MenuReadMapper;
import com.kent.hisdemo.dao.write.measure.MeasureWriteMapper;
import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.menu.Menu;
import com.kent.hisdemo.service.menu.MenuService;
import com.kent.hisdemo.vo.param.measure.MeasureParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("measureService")
public class MeasureServiceImp implements MeasureService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MeasureWriteMapper measureWriteMapper;


    @Override
    public int uploadMeasureData(MeasureParam measureParam ,Long userId) {
        Measure measure = new Measure();
        measure.setType(measureParam.getDeviceType());
        measure.setValue(measureParam.getMeasureValue());
        measure.setUserId(userId);
        measure.setCreateTime(new Date());
        return measureWriteMapper.addMeasureData(measure);
    }
}
