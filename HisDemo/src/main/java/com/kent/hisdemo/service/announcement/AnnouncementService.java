package com.kent.hisdemo.service.announcement;

import com.kent.hisdemo.entity.announcement.Announcement;
import com.kent.hisdemo.entity.feedback.Feedback;
import com.kent.hisdemo.entity.measure.Measure;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.vo.param.measure.MeasureParam;

import java.util.List;

public interface AnnouncementService {
    public List<Announcement> getAllAnnouncement(User user);

    public Announcement getLatestAnnouncement(User user);

    public Announcement getAnnouncementById(Long id);
}
