package com.kent.hisdemo.service.feedback;


import com.kent.hisdemo.dao.read.announcement.AnnouncementReadMapper;
import com.kent.hisdemo.dao.read.feedback.FeedbackReadMapper;
import com.kent.hisdemo.dao.write.feedback.FeedbackWriteMapper;
import com.kent.hisdemo.dto.feedback.FeedbackDTO;
import com.kent.hisdemo.entity.announcement.Announcement;
import com.kent.hisdemo.entity.feedback.Feedback;
import com.kent.hisdemo.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImp implements FeedbackService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    FeedbackReadMapper feedbackReadMapper;

    @Autowired
    FeedbackWriteMapper feedbackWriteMapper;

    @Override
    public int saveFeedback(Feedback feedback){
        return feedbackWriteMapper.saveFeedback(feedback);
    };
    @Override
    public int updateFeedback(Feedback feedback){
        return feedbackWriteMapper.updateFeedback(feedback);
    };
    @Override
    public List<Feedback> getAllFeedBack(){
        return feedbackReadMapper.getAllFeedBack();
    };
    @Override
    public Feedback getFeedbackById(Long id){
        return feedbackReadMapper.getFeedbackById(id);
    };


    @Override
    public List<FeedbackDTO> getAllFeedBackIncludeName() {
        return feedbackReadMapper.getAllFeedBackIncludeName();
    }

    @Override
    public int deleteFeedbackById(Long id) {
        return feedbackWriteMapper.deleteFeedbackById(id);
    }
}
