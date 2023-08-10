package com.kent.hisdemo.entity.measure;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Measure implements Serializable {
    private static final long serialVersionUID = -3141268890558850286L;
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("测量设备类型")
    private String type;
    @ApiModelProperty("测量值")
    private String value;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("被测量人用户id")
    private Long userId;
    @ApiModelProperty("反馈信息ID")
    private Long feedbackId;
}
