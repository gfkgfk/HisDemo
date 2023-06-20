package com.kent.hisdemo.vo.param.register;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class RegisterParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("登录名")
    @NotEmpty(message = "登录名不能为空")
    private String userName;

    @ApiModelProperty("用户密码")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6,message = "密码最少6位")
    private String password;

    @ApiModelProperty("手机号码")
    private String phoneNum;

    @ApiModelProperty("性别")
    private int gender;


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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
