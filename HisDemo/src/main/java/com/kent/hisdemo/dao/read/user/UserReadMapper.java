package com.kent.hisdemo.dao.read.user;

import com.kent.hisdemo.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserReadMapper {
    public User getUser(String id);

    public User getUserByNameAndPwd(User user);

    public User selectByLoginName(String userName);

    public User getUserById(Long id);
}
