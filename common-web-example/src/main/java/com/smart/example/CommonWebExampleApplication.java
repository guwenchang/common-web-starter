package com.smart.example;

import com.smart.starter.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOpLog
@EnableSmartSecurity
@EnableI18n
@EnableRedisLimit
@EnableRedisLock
@EnableXssFilter
@EnableCorsFilter
public class CommonWebExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonWebExampleApplication.class, args);
    }

}
