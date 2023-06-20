package com.kent.hisdemo.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kent.hisdemo.common.Constants;
import com.kent.hisdemo.common.ServiceResultEnum;
import com.kent.hisdemo.entity.user.User;
import com.kent.hisdemo.entity.user.UserToken;
import com.kent.hisdemo.mapper.user.UserMapper;
import com.kent.hisdemo.mapper.user.UserTokenMapper;
import com.kent.hisdemo.utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserTokenMapper userTokenMapper;
    @Autowired
    UserMapper userMapper;




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle");
        //这些接口直接放行
        if (request.getRequestURI().contains("/login") ||
            request.getRequestURI().contains("/register") ||
            request.getRequestURI().contains("/static") ||
            request.getRequestURI().contains("/error"))
        {
            return true;
        }

        //请求未携带token
        final String headerToken = request.getHeader("token");
        if (null == headerToken || headerToken.trim().equals("")) {
//            PanelException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            falseResult(response, ResultGenerator.genFailResult(ServiceResultEnum.NOT_LOGIN_ERROR.getResult()));
            return false;
        }

        //token不存在或token过期
        UserToken userToken = userTokenMapper.getUserTokenByToken(headerToken);
        if (userToken == null || userToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
//            PanelException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
            falseResult(response, ResultGenerator.genFailResult(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult()));
            return false;
        }

        //不存在对应的用户
        User user = userMapper.getUserById(userToken.getUserId());
        if (null == user) {
//            PanelException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
            falseResult(response, ResultGenerator.genFailResult(ServiceResultEnum.USER_NULL_ERROR.getResult()));
            return false;
        }

        //用户被冻结
        if (user.getLockedFlag().intValue() == 1) {
//            PanelException.fail(ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
            falseResult(response, ResultGenerator.genFailResult(ServiceResultEnum.USER_NULL_ERROR.getResult()));
            return false;
        }
        //将user放到request的attribute中
        request.setAttribute(Constants.REQUEST_USER_DATA,user);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    public void falseResult(HttpServletResponse response, Object object) throws IOException {
        logger.info("FalseResult:" + object.toString());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().println(objectMapper.writeValueAsString(object));
        return;
    }
}
