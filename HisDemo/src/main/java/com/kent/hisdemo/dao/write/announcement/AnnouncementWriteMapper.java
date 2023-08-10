package com.kent.hisdemo.dao.write.announcement;

import com.kent.hisdemo.entity.announcement.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnnouncementWriteMapper {
    public int saveAnnouncement(Announcement announcement);

    public int deleteAnnouncementById(Long id);

}
