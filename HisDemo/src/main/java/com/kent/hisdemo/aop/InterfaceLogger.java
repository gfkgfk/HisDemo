package com.kent.hisdemo.aop;

import java.lang.annotation.*;

/**
 * 自定义Web日志记录注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface InterfaceLogger {
    /**
     * 描述.
     */
    String description() default "";

    /**
     * 模块.
     */
    String module() default "";

    /**
     * 接口提供方
     * @return
     */
    String provider() default "LOCALHOST";

    /**
     * 日志级别,默认INFO级别日志.
     */
    String level() default "INFO";

    /**
     * 操作人
     */
    String operator() default "";
}
