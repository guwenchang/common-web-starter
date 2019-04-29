package com.smart.starter.annotation;

import com.smart.starter.autoconfigure.xss.XssFilterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启防 Xss 攻击
 *
 * @author Levin
 * @since 2018-01-15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({XssFilterAutoConfiguration.class})
public @interface EnableXssFilter {

}
