package com.smart.starter.autoconfigure.redis.limit;

import com.smart.starter.core.redis.DefaultRedisKeyGenerator;
import com.smart.starter.core.redis.RedisKeyGenerator;
import com.smart.starter.core.redis.limit.RedisLimitAspect;
import com.smart.starter.core.redis.limit.RedisLimitHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 *
 * @author guwenchang
 * @date 2019-05-17
 */
@Configuration
@ConditionalOnClass(RedisAutoConfiguration.class)
@RequiredArgsConstructor
public class RedisLimitAutoConfiguration{
    private static final String REDIS_LIMIT_TEMPLATE = "redisLimitTemplate";

    private final LettuceConnectionFactory lettuceConnectionFactory;

    @Bean(REDIS_LIMIT_TEMPLATE)
    public RedisTemplate<String, Serializable> redisLimitTemplate() {
        RedisTemplate<String, Serializable> redisLimitTemplate = new RedisTemplate<>();
        redisLimitTemplate.setKeySerializer(new StringRedisSerializer());
        redisLimitTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisLimitTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisLimitTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisLimitHelper redisLimitHelper() {
        return new RedisLimitHelper(redisLimitTemplate());
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisKeyGenerator redisKeyGenerator() {
        return new DefaultRedisKeyGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisLimitAspect redisLimitAspect() {
        return new RedisLimitAspect(redisLimitHelper(),redisKeyGenerator());
    }

}