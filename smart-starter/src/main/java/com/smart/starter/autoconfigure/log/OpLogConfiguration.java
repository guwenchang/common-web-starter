package com.smart.starter.autoconfigure.log;

import com.smart.starter.core.log.aspect.OpLogAspect;
import com.smart.starter.core.log.event.OpLogListener;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志自动配置类
 * @author guwenchang
 * @date 2019-04-29 14:10
 */
@EnableAsync
@ConditionalOnWebApplication
@AutoConfigureBefore(OpLogAutoConfiguration.class)
@Configuration
public class OpLogConfiguration{


	@Bean
	@ConditionalOnMissingBean
	public OpLogListener opLogListener() {
		return new OpLogListener();
	}

	@Bean
	@ConditionalOnMissingBean
	public OpLogAspect opLogAspect() {
		return new OpLogAspect();
	}


}
