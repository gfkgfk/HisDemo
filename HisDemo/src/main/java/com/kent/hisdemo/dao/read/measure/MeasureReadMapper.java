package com.kent.hisdemo.dao.read.measure;

import com.kent.hisdemo.dto.measure.MeasureDetailDTO;
import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface MeasureReadMapper {
    public List<Measure> getAllMeasureDataByUser(User user);

    public List<MeasureDetailDTO> getMeasureDetailsList();

    public Measure getMeasureDetailById(Long id);


}
