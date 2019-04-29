package com.smart.starter.core.redis;


import java.lang.annotation.*;

/**
 *
 * @author guwenchang
 * @date 2019-04-29
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisParam {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}