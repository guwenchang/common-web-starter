package com.smart.starter.core.cors;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 跨域配置
 * @author guwenchang
 * @date 2019-04-29 17:45
 */
@Data
@ConfigurationProperties("smart.cors")
public class CorsFilterProperties {

    private String origin = "*";
    private String allowedHeader = "*";
    private String method = "*";
    private String exposedHeader;
    private Boolean allowCredentials = true;


}
