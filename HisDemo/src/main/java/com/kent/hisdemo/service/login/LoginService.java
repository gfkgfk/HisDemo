package com.kent.hisdemo.service.login;

import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.vo.param.login.LoginParam;
import com.kent.hisdemo.vo.param.register.RegisterParam;
import com.kent.hisdemo.vo.vo.login.LoginVO;

public interface LoginService {
    public int registeredUser(User user);

    public User getUser(String id);

    public int updateUser(User user);

    public int unregisteredUser(String id);

    public User login(User user);

    public LoginVO login(LoginParam loginParam);

    public String register(RegisterParam registerParam);
}
