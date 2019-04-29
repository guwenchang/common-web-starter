package com.smart.starter.autoconfigure.xss;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.smart.starter.core.xss.XssFilter;
import com.smart.starter.core.xss.XssProperties;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * xss 自动配置
 * @author guwenchang
 * @date 2019-04-29 17:31
 */
@EnableConfigurationProperties(value = {XssProperties.class})
public class XssFilterAutoConfiguration {

    @Autowired(required = false)
    private XssProperties xssProperties;

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        //解析器
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        //注册xss解析器
        SimpleModule simpleModule = new SimpleModule("commonJsonSerializer");
        simpleModule.addSerializer(new CommonJsonSerializer(xssProperties));
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    static class CommonJsonSerializer extends JsonSerializer<String> {
        private XssProperties xssProperties;

        CommonJsonSerializer(XssProperties xssProperties) {
            this.xssProperties = xssProperties;
        }

        @Override
        public Class<String> handledType() {
            return String.class;
        }

        @Override
        public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (value != null) {
                if (xssProperties != null) {
                    value = StringEscapeUtils.escapeHtml4(value);
                }
                jsonGenerator.writeString(value);
            }
        }
    }

    @Bean("registrationXssFilter")
    public FilterRegistrationBean registrationXssFilter(XssProperties properties) {
        FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<>();
        // 设置过滤路径
        registrationBean.setEnabled(true);
        // 设置顺序
        registrationBean.setOrder(properties.getOrder());
        // 设置 BodyCacheFilter
        registrationBean.setFilter(new XssFilter());
        final String name = properties.getName();
        if (!StringUtils.isEmpty(name)) {
            registrationBean.setName(properties.getName());
        }
        final Map<String, String> initParameters = properties.getInitParameters();
        if (initParameters != null && initParameters.size() > 0) {
            registrationBean.setInitParameters(properties.getInitParameters());
        }
        final Set<ServletRegistrationBean<?>> registrationBeans = properties.getServletRegistrationBeans();
        if (registrationBeans != null && registrationBeans.size() > 0) {
            registrationBean.setServletRegistrationBeans(properties.getServletRegistrationBeans());
        }
        final Set<String> servletNames = properties.getServletNames();
        if (!CollectionUtils.isEmpty(servletNames)) {
            registrationBean.setServletNames(properties.getServletNames());
        }
        return registrationBean;
    }
}
