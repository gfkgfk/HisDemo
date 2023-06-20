package com.kent.hisdemo.config;

import com.kent.hisdemo.config.handler.UserMethodArgumentResolver;
import com.kent.hisdemo.config.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class HisDemoWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    UserMethodArgumentResolver userMethodArgumentResolver;

    @Autowired
    TokenInterceptor tokenInterceptor;
//    或者

//    @Bean
//    TokenInterceptor loginInterceptor() {
//        return new TokenInterceptor();
//    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userMethodArgumentResolver);
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "/static")
                .excludePathPatterns("/swagger-resources/**", "/v3/api-docs", "/swagger-ui/**");//放行swagger
//        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/static");
        WebMvcConfigurer.super.addInterceptors(registry);
    }


    /**
     * 跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true).maxAge(3600);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
