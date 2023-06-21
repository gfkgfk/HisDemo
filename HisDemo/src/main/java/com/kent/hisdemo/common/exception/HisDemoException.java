package com.kent.hisdemo.common.exception;


import com.kent.hisdemo.constant.ResponseConst;

/**
 * 系统自定义异常, 常用异常参考SLDException类
 */
public class HisDemoException extends RuntimeException {

    private static final long serialVersionUID = 2726487812574043208L;
    private int code = ResponseConst.STATE_FAIL;
    private String application;//异常产生的微服务应用名称

    public HisDemoException(String msg) {
        super(msg);
    }

    public HisDemoException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public HisDemoException(String message, String application) {
        super(message);
        this.application = application;
    }

    public HisDemoException(String message, int code, String application) {
        super(message);
        this.code = code;
        this.application = application;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

}