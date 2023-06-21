package com.kent.hisdemo.config.handler;

import com.kent.hisdemo.constant.Constants;
import com.kent.hisdemo.config.annotation.CurrentUser;
import com.kent.hisdemo.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //请求的接口上的每一个参数，都会走一次此方法，看有没有CurrentUser注解，如果有则返回true，否则返回false;返回true时会走resolveArgument,false时候不走
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if(parameter.hasParameterAnnotation(CurrentUser.class)){
            //获取前面传过来的user对象
            User user = (User) ((ServletWebRequest) webRequest).getRequest().getAttribute(Constants.REQUEST_USER_DATA);
            return user;
        }
        return null;
    }
}
