package com.kent.hisdemo.dto.measure;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MeasureDetailDTO implements Serializable {

    private static final long serialVersionUID = -9037762282979274154L;

    @ApiModelProperty("测量设备类型")
    private String deviceType;
    @ApiModelProperty("测量值")
    private String measureValue;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("被测量人用户名")
    private String userName;

}
