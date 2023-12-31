package com.kent.hisdemo.service.measure;

import com.kent.hisdemo.dto.measure.MeasureDetailDTO;
import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.menu.Menu;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.vo.param.measure.MeasureParam;

import java.util.List;

public interface MeasureService {
    public int uploadMeasureData(MeasureParam measureParam,Long userId);

    public List<Measure> getMeasureHistoryDataByUser(User user);

    public List<MeasureDetailDTO> getMeasureDetailsList();

    public Measure getMeasureDetailById(Long id);

    public int updateMeasureDetail(Measure measure);

    public int deleteMeasureDetailById(Long id);
}
