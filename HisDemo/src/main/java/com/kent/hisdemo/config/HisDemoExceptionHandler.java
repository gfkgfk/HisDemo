package com.kent.hisdemo.config;

import com.kent.hisdemo.constant.Constants;
import com.kent.hisdemo.common.exception.HisDemoException2;
import com.kent.hisdemo.common.ServiceResultEnum;
import com.kent.hisdemo.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestControllerAdvice
public class HisDemoExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {
        logger.error("BindException:"+e);
        Result result = new Result();
        result.setResultCode(Constants.NOT_NULL);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("MethodArgumentNotValidException:"+e);
        Result result = new Result();
        result.setResultCode(Constants.NOT_NULL);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest req) {
        logger.error("Exception:"+e);
        e.printStackTrace();
        Result result = new Result();
        result.setResultCode(Constants.INTERNAL_ERROR);
        //区分是否为自定义异常
        if (e instanceof HisDemoException2) {
            result.setMessage(e.getMessage());
            if (e.getMessage().equals(ServiceResultEnum.NOT_LOGIN_ERROR.getResult()) || e.getMessage().equals(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult())) {
                result.setResultCode(Constants.REQUESTED_RANGE_NOT_SATISFIABLE);
            } else if (e.getMessage().equals(ServiceResultEnum.ADMIN_NOT_LOGIN_ERROR.getResult()) || e.getMessage().equals(ServiceResultEnum.ADMIN_TOKEN_EXPIRE_ERROR.getResult())) {
                result.setResultCode(Constants.EXPIRE_ERROR);
            }
        } else {
            e.printStackTrace();
            result.setMessage("未知异常，请查看控制台日志并检查配置文件。");
        }
        return result;

    }

}
