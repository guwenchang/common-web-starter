package com.smart.starter.autoconfigure.log;

import com.smart.starter.autoconfigure.security.SmartSecurityAutoConfiguration;
import com.smart.starter.core.log.aspect.OpLogAspect;
import com.smart.starter.core.log.event.OpLogListener;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
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
	public OpLogAspect opLogAspect(ApplicationEventPublisher publisher) {
		return new OpLogAspect();
	}


}
