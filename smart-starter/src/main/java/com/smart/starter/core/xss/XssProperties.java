package com.smart.starter.core.xss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author guwenchang
 * @date 2019-04-29 17:32
 */
@Data
@ConfigurationProperties("smart.xss.filter")
public class XssProperties {

    private int order;
    private String name = "xss-filter";
    private Map<String, String> initParameters = new LinkedHashMap<>();
    private Set<String> servletNames = new LinkedHashSet<>();
    private Set<ServletRegistrationBean<?>> servletRegistrationBeans = new LinkedHashSet<>();
    private Set<String> urlPatterns = new LinkedHashSet<>();

}
