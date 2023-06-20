package com.kent.hisdemo.mapper.user;

import com.kent.hisdemo.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    public int addUser(User user);

    public User getUser(String id);

    public int updateUser(User user);

    public int deleteUser(String id);

    public User getUserByNameAndPwd(User user);

    public User selectByLoginName(String userName);

    public int register(User user);

    public User getUserById(Long id);
}
