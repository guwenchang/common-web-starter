package com.smart.starter.core.security.constants;

/**
 * 常量
 * @author guwenchang
 * @date 2019-04-22 14:49
 */
public interface ConstantsSecurity {
    /**
     * 请求头
     */
    String AUTHORIZATION_HEADER = "Authorization";
    /**
     * token 在cookie中的值
     */
    String TOKEN_COOKIE_KEY = "auth_token";

    /**
     * Bearer header
     */
    String BEARER = "Bearer ";
}
