package com.smart.starter.core.security.annotation;


import java.lang.annotation.*;


/**
 * 权限注解
 * @author guwenchang
 * @date 2019-04-22 14:07
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
	
	/**
	 * 权限内容
	 */
	String value() default "";


}