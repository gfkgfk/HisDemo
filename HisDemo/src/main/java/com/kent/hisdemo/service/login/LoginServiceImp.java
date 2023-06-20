package com.kent.hisdemo.service.login;

import com.kent.hisdemo.common.ServiceResultEnum;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.entity.user.UserToken;
import com.kent.hisdemo.mapper.user.UserMapper;
import com.kent.hisdemo.mapper.user.UserTokenMapper;
import com.kent.hisdemo.utils.MD5Util;
import com.kent.hisdemo.utils.TokenGenerator;
import com.kent.hisdemo.utils.beancopy.BeanCopyUtil;
import com.kent.hisdemo.vo.param.login.LoginParam;
import com.kent.hisdemo.vo.param.register.RegisterParam;
import com.kent.hisdemo.vo.vo.login.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("loginService")
public class LoginServiceImp implements LoginService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserTokenMapper userTokenMapper;


    public int registeredUser(User user) {
        return userMapper.addUser(user);
    }

    public User getUser(String id) {
        return userMapper.getUser(id);
    }

    public int updateUser(User user) {

        return userMapper.updateUser(user);

    }

    public int unregisteredUser(String id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public User login(User user) {
        return userMapper.getUserByNameAndPwd(user);
    }


    @Override
    public LoginVO login(LoginParam loginParam) {
        User user = new User();
        loginParam.setPassword(MD5Util.MD5Encode(loginParam.getPassword(), "UTF-8"));
        BeanCopyUtil.copyProperties(loginParam, user);
        user = userMapper.getUserByNameAndPwd(user);

        if (user != null) {
            if (user.getLockedFlag() == 1) {
                LoginVO loginVO = new LoginVO();
                BeanCopyUtil.copyProperties(user, loginVO);
                return loginVO;
            }

            //登录后即执行修改token的操作
            String token = TokenGenerator.getNewToken(System.currentTimeMillis() + "", user.getId());
            UserToken userToken = userTokenMapper.getUserTokenByUserId(user.getId());
            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);//过期时间 48 小时

            if (userToken == null) {
                userToken = new UserToken();
                userToken.setUserId(user.getId());
                userToken.setToken(token);
                userToken.setUpdateTime(now);
                userToken.setExpireTime(expireTime);
                //新增一条token数据
                if (userTokenMapper.addUserToken(userToken) > 0) {
                    //新增成功后返回
                    LoginVO loginVO = new LoginVO();
                    BeanCopyUtil.copyProperties(user, loginVO);
                    loginVO.setToken(token);
                    return loginVO;
                }
            } else {
                userToken.setToken(token);
                userToken.setUpdateTime(now);
                userToken.setExpireTime(expireTime);
                //更新
                if (userTokenMapper.updateUserToken(userToken) > 0) {
                    //修改成功后返回
                    LoginVO loginVO = new LoginVO();
                    BeanCopyUtil.copyProperties(user, loginVO);
                    loginVO.setToken(token);
                    return loginVO;
                }
            }

        }

        return null;
    }


    @Override
    public String register(RegisterParam registerParam) {
        if (userMapper.selectByLoginName(registerParam.getUserName()) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        User user = new User();
        BeanCopyUtil.copyProperties(registerParam, user);
        String passwordMD5 = MD5Util.MD5Encode(registerParam.getPassword(), "UTF-8");
        user.setPassword(passwordMD5);
        if (userMapper.register(user) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }
}
