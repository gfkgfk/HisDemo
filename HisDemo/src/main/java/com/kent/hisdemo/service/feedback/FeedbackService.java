package com.kent.hisdemo.service.feedback;

import com.kent.hisdemo.entity.announcement.Announcement;
import com.kent.hisdemo.entity.feedback.Feedback;
import com.kent.hisdemo.entity.user.User;

import java.util.List;

public interface FeedbackService {
    public int saveFeedback(Feedback feedback);

    public int updateFeedback(Feedback feedback);

    public List<Feedback> getAllFeedBack();

    public Feedback getFeedbackById(Long id);

}
