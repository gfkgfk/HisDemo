package com.kent.hisdemo.utils;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.kent.hisdemo.entity.system.pojo.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
public class InterfaceLogUtil implements InitializingBean {

    public static InterfaceLogUtil interfaceLogUtil;

    @Override
    public void afterPropertiesSet() throws Exception {
        interfaceLogUtil = this;
    }

    /**
     * @param request
     * @param module      对接系统
     * @param description 接口描述
     * @param parameters  请求参数
     * @param result      返回结果
     * @param method      请求类型
     * @param code        返回状态
     * @param start       开始执行时间
     * @param duration    执行时长
     */
    public static void pushInterfaceLog(HttpServletRequest request, String module, String description
            , String parameters, Object result, String method, Integer code, Date start,
                                        long duration, String operator, String provider) {

        pushInterfaceLog(module, description, request.getRequestURI()
                , parameters, result, method, code, start, duration
                , WebUtil.getRealIp(request), operator, provider);
    }

    /**
     * @param module      对接系统
     * @param description 接口
     * @param url         请求地址
     * @param parameters  请求参数
     * @param result      返回结果
     * @param method      调用方法
     * @param code        返回状态
     * @param start       开始时间
     * @param duration    时长
     * @param ip          IP
     */
    public static void pushInterfaceLog(String module, String description, String url
            , String parameters, Object result, String method, Integer code
            , Date start, long duration, String ip, String operator, String provider) {

        SystemLog logInfo = new SystemLog()
                .setModule(module)
                .setDescription(description)
                .setMethod(method)
                .setLogUrl(url)
                .setParameters(parameters)
                .setOperationTime(start)
                .setLogIp(ip)
                .setOperator(operator)
                .setDuration(duration)
                .setCode(code)
                .setProvider(provider);

        if (result instanceof HttpResponse) {
            logInfo.setResult(((HttpResponse) result).body());
        } else {
            try {
                logInfo.setResult(JSON.toJSONString(result));
            } catch (Exception e) {
                logInfo.setResult(result + "\r\n系统接口日志设置返回值异常:" + e.getMessage());
            }
        }
        // 接口暂时记录到控制台
        if (code == 200) {
            log.info(logInfo.toString());
        } else {
            log.error(logInfo.toString());
        }
    }
}
