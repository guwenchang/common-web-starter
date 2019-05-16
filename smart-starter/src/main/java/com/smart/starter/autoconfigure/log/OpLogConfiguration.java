package com.smart.starter.autoconfigure.log;

import com.smart.starter.core.log.ConstantsLog;
import com.smart.starter.core.log.aspect.OpLogAspect;
import com.smart.starter.core.log.event.OpLogHandler;
import com.smart.starter.core.log.event.OpLogListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

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


	@Resource
	private RabbitTemplate rabbitTemplate;

	@Bean
	@ConditionalOnMissingBean
	public OpLogListener opLogListener() {
		return new OpLogListener(rabbitTemplate);
	}

	@Bean
	@ConditionalOnMissingBean
	public OpLogAspect opLogAspect() {
		return new OpLogAspect();
	}

	@Bean
	@ConditionalOnMissingBean
	public OpLogHandler opLogHandler() {
		return new OpLogHandler();
	}



	@Bean
	public Queue opLogQueue() {
		// 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
		return new Queue(ConstantsLog.OP_LOG_QUEUE, true);
	}
}
