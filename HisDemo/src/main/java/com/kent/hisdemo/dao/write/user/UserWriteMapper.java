package com.kent.hisdemo.dao.write.user;

import com.kent.hisdemo.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserWriteMapper {
    public int addUser(User user);

    public int updateUser(User user);

    public int deleteUser(String id);

    public int register(User user);
}
