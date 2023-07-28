package com.kent.hisdemo.vo.param.measure;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MeasureParam implements Serializable {

    private static final long serialVersionUID = 4271284723063463555L;

    @ApiModelProperty(value = "设备类型",required=true)
    @NotNull(message = "设备类型不能为空")
    String deviceType;

    @ApiModelProperty(value = "测量值",required=true)
    @NotNull(message = "测量值不能为空")
    String measureValue;

    @Override
    public String toString() {
        return "MeasureParam{" +
                ", deviceType='" + deviceType + '\'' +
                ", measureValue='" + measureValue + '\'' +
                '}';
    }
}
