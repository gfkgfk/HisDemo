package com.kent.hisdemo.vo.vo.community;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AnnouncementVO implements Serializable {
    private static final long serialVersionUID = -8984431988396047129L;

    private Long id;
    private String title;
    private String content;
    private Date createTime;
}
