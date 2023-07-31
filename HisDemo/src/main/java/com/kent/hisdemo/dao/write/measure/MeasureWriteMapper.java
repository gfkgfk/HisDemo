package com.kent.hisdemo.dao.write.measure;

import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.user.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MeasureWriteMapper {
    public int updateMeasureData(Measure measure);

    public int addMeasureData(Measure measure);
}
