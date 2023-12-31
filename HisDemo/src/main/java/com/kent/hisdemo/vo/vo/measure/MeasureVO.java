package com.kent.hisdemo.vo.vo.measure;

import com.kent.hisdemo.entity.feedback.Feedback;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MeasureVO implements Serializable {

    private static final long serialVersionUID = 4271284723063463555L;
    @ApiModelProperty("设备类型")
    String deviceType;
    @ApiModelProperty("测量值")
    String measureValue;
    @ApiModelProperty("评估/反馈信息")
    Feedback feedback;

    @Override
    public String toString() {
        return "MeasureParam{" +
                ", deviceType='" + deviceType + '\'' +
                ", measureValue='" + measureValue + '\'' +
                '}';
    }
}
