package com.kent.hisdemo.entity.announcement;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Announcement implements Serializable {
    private static final long serialVersionUID = 463266034237796815L;

    private Long id;
    private String title;
    private String content;
    private Date createTime;
    

}
