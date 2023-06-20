package com.kent.hisdemo.controller.login;

import com.kent.hisdemo.common.ServiceResultEnum;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.service.login.LoginService;
import com.kent.hisdemo.utils.ResultGenerator;
import com.kent.hisdemo.vo.Result;
import com.kent.hisdemo.vo.param.login.LoginParam;
import com.kent.hisdemo.vo.param.register.RegisterParam;
import com.kent.hisdemo.vo.vo.login.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "v1", tags = "1.HisDemo登录功能接口")
@RequestMapping("/api/v1")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String updateUser(User user) {
        int ret = loginService.updateUser(user);
        return null;
    }

    @RequestMapping(value = "/unegistereduser", method = RequestMethod.POST)
    public String unregisteredUser(String id) {
        int ret = loginService.unregisteredUser(id);
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @CrossOrigin
    @ApiOperation(value = "登录接口", notes = "返回token")
    public Result<String> login(@Valid LoginParam loginParam) {
        System.out.println("登录接口");
        LoginVO loginVO = loginService.login(loginParam);
        Result<String> result;
        if (null == loginVO) {
            result = ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_USER_PWD_ERROR.getResult());
        } else if (loginVO.getLockedFlag() == 1) {
            result = ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
        } else if (loginVO.getIsDeleted() == 1) {
            result = ResultGenerator.genFailResult(ServiceResultEnum.USER_NULL_ERROR.getResult());
        } else {
            result = ResultGenerator.genSuccessResult(loginVO);
        }
        return result;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    @CrossOrigin
    @ApiOperation(value = "注册接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "phoneNum", value = "手机号码", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "gender", value = "性别", required = true, dataTypeClass = String.class)
    })
    public Result<String> register(@Valid RegisterParam registerParam) {
        String registerResult = loginService.register(registerParam);

        //注册成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            return ResultGenerator.genSuccessResult();
        }

        //失败
        return ResultGenerator.genFailResult(registerResult);
    }

}
