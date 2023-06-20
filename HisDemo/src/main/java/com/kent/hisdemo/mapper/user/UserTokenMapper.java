package com.kent.hisdemo.mapper.user;

import com.kent.hisdemo.entity.user.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserTokenMapper {
    public UserToken getUserTokenByUserId(Long id);

    public int addUserToken(UserToken userToken);

    public int updateUserToken(UserToken userToken);

    public UserToken getUserTokenByToken(String token);

}
