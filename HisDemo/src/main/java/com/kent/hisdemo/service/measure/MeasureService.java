package com.kent.hisdemo.service.measure;

import com.kent.hisdemo.entity.menu.Menu;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.vo.param.measure.MeasureParam;

import java.util.List;

public interface MeasureService {
    public int uploadMeasureData(MeasureParam measureParam,Long userId);

}
