package com.smart.starter.core.log.aspect;

import com.alibaba.fastjson.JSON;
import com.smart.starter.core.log.OpLogParam;
import com.smart.starter.core.log.annotation.OpLog;
import com.smart.starter.core.log.event.OpLogEvent;
import com.smart.starter.core.log.util.OpLogUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 日志切面
 * @author guwenchang
 * @date 2019-04-28 16:35
 */
@Slf4j
@Aspect
public class OpLogAspect {

	@Resource
	private ApplicationEventPublisher publisher;

	/**
	 * 用于获取应用名称
	 */
	@Resource
	private Environment environment;

	@Pointcut("execution(public * com.smart..*Controller.*(..))")
	public void webLog() {
	}

	@SneakyThrows
	@Around("webLog()")
	public Object around(ProceedingJoinPoint point) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature)signature;
		Method targetMethod = methodSignature.getMethod();
		Long startTime = System.currentTimeMillis();
		Object obj = point.proceed();
		Long endTime = System.currentTimeMillis();
		log.debug("response : {}", JSON.toJSONString(obj));
		log.debug("spendTime : {}", (endTime - startTime));
		OpLog opLog = targetMethod.getAnnotation(OpLog.class);
		if (opLog != null){
			OpLogParam opLogParam = OpLogUtils.getopLog();
			opLogParam.setApp(environment.getProperty("spring.application.name"));
			opLogParam.setTitle(opLog.value());
			opLogParam.setType(opLog.type());
			opLogParam.setTime(endTime - startTime);
			// 发送异步日志事件
			publisher.publishEvent(new OpLogEvent(opLogParam));
		}
		return obj;
	}

}
