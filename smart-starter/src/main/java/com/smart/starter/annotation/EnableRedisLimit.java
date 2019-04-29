package com.smart.starter.annotation;

import com.smart.starter.autoconfigure.redis.limit.RedisLimitAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @author guwenchang
 * @date 2019-04-29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({RedisLimitAutoConfiguration.class})
public @interface EnableRedisLimit {

}
