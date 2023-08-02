package com.kent.hisdemo.controller.user;

import com.kent.hisdemo.common.response.JsonResult;
import com.kent.hisdemo.common.response.SldResponse;
import com.kent.hisdemo.config.annotation.CurrentUser;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.service.login.LoginService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "v1", tags = "3.用户信息接口")
@RequestMapping("/api/v1/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public JsonResult<User> getUserInfo(@CurrentUser User user) {
        return SldResponse.success(user);
    }
}
