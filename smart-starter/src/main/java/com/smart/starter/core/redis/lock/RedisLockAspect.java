package com.smart.starter.core.redis.lock;


import com.smart.starter.core.redis.RedisKeyGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.UUID;

/**
 *
 * @author guwenchang
 * @date 2019-04-29
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RedisLockAspect {
    private final RedisLockHelper redisLockHelper;
    private final RedisKeyGenerator redisKeyGenerator;


    @Around("@annotation(redisLock)")
    public Object interceptor(ProceedingJoinPoint pjp,RedisLock redisLock) {
        if (StringUtils.isEmpty(redisLock.prefix())) {
            throw new RuntimeException("lock key prefix don't null...");
        }
        final String lockKey = redisKeyGenerator.generate(redisLock.prefix(), redisLock.delimiter(), pjp);
        String value = UUID.randomUUID().toString();
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            final boolean success = redisLockHelper.lock(lockKey, value, redisLock.expire(), redisLock.timeUnit());
            if (log.isDebugEnabled()) {
                log.debug("Redis lock key is [{}] and status is [{}]", lockKey, success);
            }
            if (!success) {
                throw new RuntimeException(redisLock.message());
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("server exception");
            }
        } finally {
            redisLockHelper.unlock(lockKey, value);
        }
    }
}