package com.kent.hisdemo.dao.read.announcement;

import com.kent.hisdemo.entity.announcement.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnnouncementReadMapper {
    public List<Announcement> getAllAnnouncement();

    public Announcement getLatestAnnouncement();

    public Announcement getAnnouncementById(Long id);


}
