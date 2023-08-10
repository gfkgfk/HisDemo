package com.kent.hisdemo.vo.vo.feedback;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FeedbackDetailVO implements Serializable {
    private static final long serialVersionUID = -477768039700141088L;

    @ApiModelProperty("主键")
    Long id;
    @ApiModelProperty("反馈内容")
    String content;
    @ApiModelProperty("反馈填写医生ID")
    Long userId;
    @ApiModelProperty("反馈填写医生名字")
    String userName;

}
