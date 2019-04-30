package com.smart.starter.core.security.annotation;

import java.lang.annotation.*;

/**
 * 登录注解
 * @author guwenchang
 * @date 2019-04-22 14:07
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {

	/**
	 * 执行动作
	 * {@link Action}
	 */
	Action action() default Action.NORMAL;
	
}
