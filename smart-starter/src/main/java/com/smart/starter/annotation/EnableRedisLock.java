package com.smart.starter.annotation;

import com.smart.starter.autoconfigure.redis.lock.RedisLockAutoConfiguration;
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
@Import({RedisLockAutoConfiguration.class})
public @interface EnableRedisLock {


}
