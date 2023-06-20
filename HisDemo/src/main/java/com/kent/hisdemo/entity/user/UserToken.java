package com.kent.hisdemo.entity.user;

import java.io.Serializable;
import java.util.Date;

public class UserToken implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String token;
    private Date updateTime;
    private Date expireTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", updateTime=" + updateTime +
                ", expireTime=" + expireTime +
                '}';
    }
}
