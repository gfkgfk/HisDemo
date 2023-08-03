package com.kent.hisdemo.entity.announcement;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Announcement implements Serializable {
    private static final long serialVersionUID = 463266034237796815L;
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("公告标题")
    private String title;
    @ApiModelProperty("公告内容")
    private String content;
    @ApiModelProperty("创建时间")
    private Date createTime;
    

}
