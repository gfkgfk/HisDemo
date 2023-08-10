package com.kent.hisdemo.dao.write.feedback;

import com.kent.hisdemo.entity.feedback.Feedback;
import com.kent.hisdemo.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FeedbackWriteMapper {
    public int saveFeedback(Feedback feedback);

    public int updateFeedback(Feedback feedback);

    public int deleteFeedbackById(Long id);

}
