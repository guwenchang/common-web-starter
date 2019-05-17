package com.smart.starter.core.redis;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * redis key生成器
 * @author guwenchang
 * @date 2019-04-29
 */
public class DefaultRedisKeyGenerator implements RedisKeyGenerator {

    @Override
    public String generate(String prefix, String delimiter, ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        //  默认解析方法里面带 RedisParam注解的属性,
        for (int i = 0; i < parameters.length; i++) {
            final RedisParam annotation = parameters[i].getAnnotation(RedisParam.class);
            if (annotation == null) {
                continue;
            }
            builder.append(delimiter).append(args[i]);
        }
        return prefix + builder.toString();
    }
}