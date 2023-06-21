package com.kent.hisdemo.aop;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kent.hisdemo.common.exception.HisDemoException;
import com.kent.hisdemo.dto.aop.ExchangeSignParamDTO;
import com.kent.hisdemo.utils.InterfaceLogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 自定义AOP+注解方式记录controller层日志
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class InterfaceLogAspect {

    @Around("@annotation(InterfaceLogger)")
    public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = null;
        if (requestAttributes != null) {
            request = requestAttributes.getRequest();
        }
        if (request == null) {
            return joinPoint.proceed();
        }
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        InterfaceLogger methodInterfaceLogger = method.getAnnotation(InterfaceLogger.class);
        // 接口方法描述
        String description = methodInterfaceLogger.description();
        // 优先从方法注解获取模块名
        String module = methodInterfaceLogger.module();
        if (StrUtil.isEmpty(module)) {
            Class<?> clazz = method.getDeclaringClass();
            InterfaceLogger clazzInterfaceLogger = clazz.getAnnotation(InterfaceLogger.class);
            if (clazzInterfaceLogger != null && StrUtil.isNotEmpty(clazzInterfaceLogger.module())) {
                // 其次从类注解获取模块名
                module = clazzInterfaceLogger.module();
            } else {
                // 最后获取包名作为模块名
                module = clazz.getPackage().getName();
            }
        }

        Object[] args = joinPoint.getArgs();
        List parameterList = new ArrayList();
        String parameter = null;
        String operator = null;
        // 参数处理
        try {
            for (Object p : args) {
                if (p instanceof ExchangeSignParamDTO) {
                    parameter = ((ExchangeSignParamDTO) p).getBody().toString();
                    operator = ((ExchangeSignParamDTO) p).getAppkey();
                    break;
                }
                if (!(p instanceof ServletRequest) && !(p instanceof ServletResponse)) {
                    parameterList.add(p);
                }
            }
            if (parameter == null && parameterList.size() > 0) {
                if (parameterList.size() == 1) {
                    parameter = JSONObject.toJSONString(parameterList.get(0));
                } else {
                    parameter = JSONArray.toJSONString(parameterList);
                }
            }
        } catch (Exception e) {
            parameter = Arrays.toString(joinPoint.getArgs());
        }

        long start = System.currentTimeMillis();

        Object result = null;
        Integer code = 200;
        long duration = 0;
        try {

            result = joinPoint.proceed();
            if (result == null) {
                result = requestAttributes.getResponse();
            }
            duration = System.currentTimeMillis() - start;

            return result;
        } catch (Throwable e) {
            duration = System.currentTimeMillis() - start;
            if (e instanceof HisDemoException) {
                result = e.getMessage();
                code = ((HisDemoException) e).getCode();
            } else {
                code = 500;
                result = getExceptionStr(e);
            }
            throw e;
        } finally {
            InterfaceLogUtil.pushInterfaceLog(request, module, description, parameter, result,
                    signature.toString(), code, new Date(start)
                    , duration, operator, methodInterfaceLogger.provider());
        }
    }


    /**
     * 根据异常生成记录在数据库中的字符串
     *
     * @param e 异常对象
     * @return 异常信息
     */
    private static String getExceptionStr(Throwable e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        int splitSize = 20;
        StringBuilder sb = new StringBuilder();
        sb.append(e.getMessage()).append("=>");
        StackTraceElement[] outArray;
        if (stackTrace != null && stackTrace.length >= splitSize) {
            outArray = new StackTraceElement[splitSize];
            System.arraycopy(stackTrace, 0, outArray, 0, splitSize);
        } else {
            outArray = stackTrace;
        }
        if (outArray != null && outArray.length > 0) {
            for (StackTraceElement element : outArray) {
                if (element == null) {
                    continue;
                }
                sb.append(element.getClassName())
                        .append(",").append(element.getLineNumber())
                        .append(",").append(element.getMethodName())
                        .append(";\n");
            }
        }
        return sb.toString();
    }


}










