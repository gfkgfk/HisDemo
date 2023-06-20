package com.kent.hisdemo.dao.write.user;

import com.kent.hisdemo.entity.user.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserTokenWriteMapper {

    public int addUserToken(UserToken userToken);

    public int updateUserToken(UserToken userToken);

}
