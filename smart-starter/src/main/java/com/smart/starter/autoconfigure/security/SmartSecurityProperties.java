package com.smart.starter.autoconfigure.security;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


/**
 * 配置属性
 * @author guwenchang
 * @date 2019-04-22 15:48
 */
@Data
@ConfigurationProperties(prefix = "smart.security")
public class SmartSecurityProperties {
    /**
     * 权限忽律的url
     */
    private List<String> ignoreUrls = new ArrayList<>();


    private Jwt jwt = new Jwt();

    @Data
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

