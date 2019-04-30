package com.smart.starter.core.redis.lock;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author guwenchang
 * @date 2019-04-29
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLock {

    /**
     * redis 锁key的前缀
     *
     * @return redis 锁key的前缀
     */
    String prefix() default "";

    /**
     * 过期秒数,默认为5秒
     *
     * @return 轮询锁的时间
     */
    int expire() default 5;

    /**
     * 超时时间单位
     *
     * @return 秒
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;


    /**
     *上锁以后xxx秒自动解锁
     * @return
     */
    long leaseTime() default 10;


    /**
     * 释放时间单位
     * @return
     */
    TimeUnit leaseTimeUnit() default TimeUnit.SECONDS;

    /**
     * <p>Key的分隔符（默认 :）</p>
     * <p>生成的Key：N:SO1008:500</p>
     *
     * @return String
     */
    String delimiter() default ":";


    /**
     * 提示消息
     *
     * @return String
     */
    String message() default "";
}