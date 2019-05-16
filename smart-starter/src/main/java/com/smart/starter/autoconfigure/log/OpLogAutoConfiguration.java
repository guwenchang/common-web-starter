package com.smart.starter.autoconfigure.log;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 日志自动配置类
 * @author guwenchang
 * @date 2019-04-29 14:10
 */
@Configuration
@Import(OpLogConfiguration.class)
public class OpLogAutoConfiguration {

}
