package com.smart.starter.autoconfigure.cors;

import cn.hutool.core.util.StrUtil;
import com.smart.starter.core.cors.CorsFilterProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author guwenchang
 * @date 2019-04-29 17:48
 */
@Configuration
@EnableConfigurationProperties(value = {CorsFilterProperties.class})
@RequiredArgsConstructor
public class CorsFilterAutoConfiguration {

    private static final String PATH = "/**";

    private final CorsFilterProperties properties;


    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(StrUtil.blankToDefault(properties.getOrigin(), CorsConfiguration.ALL));
        corsConfiguration.addAllowedHeader(StrUtil.blankToDefault(properties.getAllowedHeader(), CorsConfiguration.ALL));
        corsConfiguration.addAllowedMethod(StrUtil.blankToDefault(properties.getMethod(), CorsConfiguration.ALL));
        // 是否发送 Cookie 信息
        corsConfiguration.setAllowCredentials(properties.getAllowCredentials());
        if (properties.getMaxAge() != null) {
            corsConfiguration.setMaxAge(properties.getMaxAge());
        }
        if (properties.getExposedHeader() != null) {
            corsConfiguration.addExposedHeader(properties.getExposedHeader());
        }
        return corsConfiguration;
    }

    /**
     * 跨域过滤器
     *
     * @return Cors过滤器
     */
    @Bean
    @ConditionalOnMissingBean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(StrUtil.blankToDefault(properties.getPath(), PATH), buildConfig());
        return new CorsFilter(source);
    }

}
