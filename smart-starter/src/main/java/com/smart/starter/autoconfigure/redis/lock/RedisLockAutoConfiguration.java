package com.smart.starter.autoconfigure.redis.lock;

import com.smart.starter.core.log.aspect.OpLogAspect;
import com.smart.starter.core.redis.DefaultRedisKeyGenerator;
import com.smart.starter.core.redis.RedisKeyGenerator;
import com.smart.starter.core.redis.lock.RedisLockAspect;
import com.smart.starter.core.redis.lock.RedisLockHelper;
import com.smart.starter.core.security.jwt.JwtOperator;
import com.smart.starter.core.security.jwt.UserOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 *
 * @author guwenchang
 * @date 2019-04-29
 */
@Slf4j
@Configuration
@ConditionalOnClass(RedisAutoConfiguration.class)
public class RedisLockAutoConfiguration {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    @ConditionalOnMissingBean
    public RedisLockHelper redisLockHelper() {
        return new RedisLockHelper(stringRedisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisKeyGenerator redisKeyGenerator() {
        return new DefaultRedisKeyGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisLockAspect redisLockAspect() {
        return new RedisLockAspect(redisLockHelper(),redisKeyGenerator());
    }


}
