package com.smart.starter.core.redis.limit;

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
public @interface RedisLimit {

    /**
     * Key的prefix
     *
     * @return String
     */
    String prefix() default "";


    /**
     * <p>Key的分隔符（默认 :）</p>
     * <p>生成的Key：N:SO1008:500</p>
     *
     * @return String
     */
    String delimiter() default ":";

    /**
     * 过期时间
     *
     * @return int
     */
    int expire() default 2;

    /**
     * 过期时间单位
     *
     * @return 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 最多的访问限制次数
     *
     * @return int
     */
    long count() default 5;


    /**
     * 默认异常信息
     *
     * @return String
     */
    String message() default "You have been dragged into the blacklist";

    /**
     * 限流描述
     *
     * @return String
     */
    String description() default "access limit";


}
