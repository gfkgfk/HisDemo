package com.kent.hisdemo.entity.system.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;

/**
 * 系统接口日志表
 */
@Data
@Accessors(chain = true)
public class SystemLog implements Serializable {

    public static final String NAME = "com.juyuan.b2b2c.seller.pojo.WebLogInfo";

    @ApiModelProperty("日志id")
    private Integer logId;

    @ApiModelProperty(value = "操作人id")
    private Long operatorId;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty(value = "模块名")
    private String module;

    @ApiModelProperty(value = "接口描述")
    private String description;

    @ApiModelProperty(value = "接口方法")
    private String method;

    @ApiModelProperty(value = "接口地址")
    private String logUrl;

    @ApiModelProperty(value = "请求参数")
    private String parameters;

    @ApiModelProperty(value = "操作时间")
    private Date operationTime;

    @ApiModelProperty(value = "返回结果")
    private Object result;

    @ApiModelProperty(value = "请求IP")
    private String logIp;

    @ApiModelProperty(value = "请求处理时间")
    private Long duration;

    @ApiModelProperty(value = "结果代码")
    private Integer code;

    @ApiModelProperty(value = "日志类型，0-商城系统日志,1-易复诊接口日志,2-苍穹接口日志")
    private Integer logType;
    @ApiModelProperty(value = "接口提供方")
    private String provider;

    @Override
    public String toString() {
        String logString = "\r\n接口名称：【{0}-{1}】  接口提供方：{2}  调用方：{3}  IP:{4}  开始时间：{5}   用时：{6}\r\n" +
                "请求地址：{7}   返回状态：{8} 调用方法：{9}  \r\n" +
                "请求参数：{10} \r\n" +
                "返回值：{11}";
        return MessageFormat.format(logString,getModule(),getDescription(),getProvider(),getOperator(),getLogIp(),getOperationTime(),getDuration()
        ,getLogUrl(),getCode(),getMethod()
        ,getParameters(),getResult());
    }
}
