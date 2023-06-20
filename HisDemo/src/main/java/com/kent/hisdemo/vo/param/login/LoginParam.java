package com.kent.hisdemo.vo.param.login;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LoginParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名",required=true)
    @NotNull(message = "登录名不能为空")
    String userName;

    @ApiModelProperty(value = "密码",required=true)
    @NotNull(message = "密码不能为空")
    String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginParam{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
