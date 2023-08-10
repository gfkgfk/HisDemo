package com.kent.hisdemo.dao.read.feedback;

import com.kent.hisdemo.entity.announcement.Announcement;
import com.kent.hisdemo.entity.feedback.Feedback;
import com.kent.hisdemo.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FeedbackReadMapper {
    public List<Feedback> getAllFeedBack();

    public Feedback getFeedbackById(Long id);
    public int getCount(Long id);
}
