package com.smart.starter.autoconfigure.redis.limit;

import com.smart.starter.core.redis.DefaultRedisKeyGenerator;
import com.smart.starter.core.redis.RedisKeyGenerator;
import com.smart.starter.core.redis.limit.RedisLimitAspect;
import com.smart.starter.core.redis.limit.RedisLimitHelper;
import com.smart.starter.core.redis.lock.RedisLockAspect;
import com.smart.starter.core.redis.lock.RedisLockHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author Levin
 * @since 2018/12/24 0024
 */
@Slf4j
@ConditionalOnClass(RedisAutoConfiguration.class)
public class RedisLimitAutoConfiguration{


    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;


    private static final String REDIS_LIMIT_TEMPLATE = "redisLimitTemplate";

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