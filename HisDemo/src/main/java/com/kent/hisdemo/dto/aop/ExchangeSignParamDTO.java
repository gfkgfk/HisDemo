package com.kent.hisdemo.dto.aop;

import lombok.Data;

import java.io.Serializable;

/**
 * 第三方系统接口公共参数DTO
 */
@Data
public class ExchangeSignParamDTO implements Serializable {
    private static final long serialVersionUID = 6109936539389073466L;
    /**
     * appkey
     */
    private String appkey;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 数据签名
     */
    private String sign;
    /**
     * 业务参数
     */
    private Object body;
}
