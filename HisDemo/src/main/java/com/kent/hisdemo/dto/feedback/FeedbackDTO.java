package com.kent.hisdemo.dto.feedback;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FeedbackDTO implements Serializable {

    private static final long serialVersionUID = 9123706004851340431L;
    @ApiModelProperty("主键")
    Long id;
    @ApiModelProperty("反馈内容")
    String content;
    @ApiModelProperty("反馈填写医生名称")
    String userName;

}
