package com.smart.example;

import com.smart.starter.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableOpLog
@EnableSmartSecurity
@EnableI18n
@EnableRedisLimit
@EnableRedisLock
@EnableXssFilter
@EnableCorsFilter
@EnableSwagger2Doc
public class CommonWebExampleApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(CommonWebExampleApplication.class, args);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
