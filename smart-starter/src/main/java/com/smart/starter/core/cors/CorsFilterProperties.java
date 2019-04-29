package com.smart.starter.core.cors;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

/**
 *
 * @author guwenchang
 * @date 2019-04-29 17:45
 */
@Data
@ConfigurationProperties("request.cors")
public class CorsFilterProperties {

    private Boolean enabled;
    private String path;
    private String origin;
    private String allowedHeader;
    private String method;
    private String exposedHeader;

    @Nullable
    private Boolean allowCredentials;

    @Nullable
    private Long maxAge;

}
