package com.kent.hisdemo.entity.feedback;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Feedback implements Serializable {

    private static final long serialVersionUID = -1546354873980408191L;
    @ApiModelProperty("主键")
    Long id;
    @ApiModelProperty("反馈内容")
    String content;
    @ApiModelProperty("反馈填写医生ID")
    Long userId;


}
