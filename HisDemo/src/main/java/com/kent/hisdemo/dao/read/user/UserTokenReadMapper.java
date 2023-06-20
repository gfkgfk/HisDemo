package com.kent.hisdemo.dao.read.user;

import com.kent.hisdemo.entity.user.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserTokenReadMapper {
    public UserToken getUserTokenByUserId(Long id);

    public UserToken getUserTokenByToken(String token);

}
