package com.smart.example;

import com.smart.starter.annotation.EnableOpLog;
import com.smart.starter.annotation.EnableSmartSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOpLog
@EnableSmartSecurity
public class CommonWebExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonWebExampleApplication.class, args);
    }

}
