package com.smart.starter.core.redis.limit;

import com.smart.starter.core.redis.RedisKeyGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.types.Expiration;

import javax.annotation.Resource;

/**
 *
 * @author guwenchang
 * @date 2019-04-29
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RedisLimitAspect {

    private final RedisLimitHelper redisLimitHelper;
    private final RedisKeyGenerator redisKeyGenerator;


    @Around("@annotation(redisLimit)")
    public Object interceptor(ProceedingJoinPoint pjp,RedisLimit redisLimit) {
        final String prefix = redisLimit.prefix();
        final String delimiter = redisLimit.delimiter();
        final String description = redisLimit.description();
        final long count = redisLimit.count();
        final long limitExpire = redisLimit.expire();
        final long seconds = Expiration.from(limitExpire, redisLimit.timeUnit()).getExpirationTimeInSeconds();
        String key = redisKeyGenerator.generate(prefix, delimiter, pjp);
        try {
            final boolean acquire = this.redisLimitHelper.tryAcquire(key, count, seconds, description);
            if (acquire) {
                return pjp.proceed();
            } else {
                throw new RuntimeException(redisLimit.message());
            }
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
            throw new RuntimeException("server exception");
        }
    }


}