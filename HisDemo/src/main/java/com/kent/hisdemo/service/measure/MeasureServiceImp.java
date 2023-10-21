package com.kent.hisdemo.service.measure;


import com.kent.hisdemo.dao.read.measure.MeasureReadMapper;
import com.kent.hisdemo.dao.read.menu.MenuReadMapper;
import com.kent.hisdemo.dao.write.measure.MeasureWriteMapper;
import com.kent.hisdemo.dto.measure.MeasureDetailDTO;
import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.menu.Menu;
import com.kent.hisdemo.entity.user.User;
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
    @Autowired
    MeasureReadMapper measureReadMapper;


    @Override
    public int uploadMeasureData(MeasureParam measureParam, Long userId) {
        Measure measure = new Measure();
        measure.setType(measureParam.getDeviceType());
        measure.setValue(measureParam.getMeasureValue());
        measure.setUserId(userId);
        measure.setCreateTime(new Date());
        measure.setFeedbackId(-1L);
        return measureWriteMapper.addMeasureData(measure);
    }


    @Override
    public List<Measure> getMeasureHistoryDataByUser(User user) {
        List<Measure> list = measureReadMapper.getAllMeasureDataByUser(user);
        return list;
    }

    @Override
    public List<MeasureDetailDTO> getMeasureDetailsList() {
        List<MeasureDetailDTO> list = measureReadMapper.getMeasureDetailsList();
        return list;
    }


    @Override
    public Measure getMeasureDetailById(Long id) {
        return measureReadMapper.getMeasureDetailById(id);
    }

    @Override
    public int updateMeasureDetail(Measure measure) {
        return measureWriteMapper.updateMeasureDetail(measure);
    }

    @Override
    public int deleteMeasureDetailById(Long id) {
        return measureWriteMapper.deleteMeasureDetailById(id);
    }
}
