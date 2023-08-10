package com.kent.hisdemo.service.feedback;

import com.kent.hisdemo.dto.feedback.FeedbackDTO;
import com.kent.hisdemo.entity.announcement.Announcement;
import com.kent.hisdemo.entity.feedback.Feedback;
import com.kent.hisdemo.entity.user.User;

import java.util.List;

public interface FeedbackService {
    public int saveFeedback(Feedback feedback);

    public int updateFeedback(Feedback feedback);

    public List<Feedback> getAllFeedBack();

    public List<FeedbackDTO> getAllFeedBackIncludeName();

    public Feedback getFeedbackById(Long id);

    public int deleteFeedbackById(Long id);


}
