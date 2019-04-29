package com.smart.starter.autoconfigure.security;


import com.smart.starter.core.security.advice.SmartSecurityExceptionHandler;
import com.smart.starter.core.security.filter.SmartSecurityContextHolderFilter;
import com.smart.starter.core.security.jwt.JwtOperator;
import com.smart.starter.core.security.jwt.UserOperator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * @author guwenchang
 * @date 2019-04-22 15:48
 */
@Configuration
@AutoConfigureBefore(SmartSecurityAutoConfiguration.class)
@EnableConfigurationProperties(SmartSecurityProperties.class)
public class SmartSecurityConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public JwtOperator jwtOperator(SmartSecurityProperties smartSecurityProperties) {
        return new JwtOperator(smartSecurityProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public UserOperator userOperator(JwtOperator jwtOperator) {
        return new UserOperator(jwtOperator);
    }

    @Bean
    @ConditionalOnMissingBean
    public SmartSecurityExceptionHandler smartSecurityExceptionHandler() {
        return new SmartSecurityExceptionHandler();
    }

    @Bean
    public FilterRegistrationBean securityFilterRegistration1(UserOperator userOperator) {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加我们写好的过滤器
        registration.setFilter( new SmartSecurityContextHolderFilter(userOperator));
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        return registration;
    }
}

