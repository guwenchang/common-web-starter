package com.smart.starter.autoconfigure.security;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 配置属性
 * @author guwenchang
 * @date 2019-04-22 15:48
 */
@ConfigurationProperties(prefix = "smart.security")
@Getter
@Setter
public class SmartSecurityProperties {
    private Jwt jwt = new Jwt();
    @Getter
    @Setter
    public static class Jwt {
        /**
         * secret
         */
        private String secret = "smart-security";

        /**
         * token的有效时间(秒)，默认2周
         */
        private Long expirationInSecond = 1209600L;

        /**
         * 加签的算法，默认sha512
         */
        private SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
    }
}

