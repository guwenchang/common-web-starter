package com.smart.starter.annotation;

import com.smart.starter.autoconfigure.cors.CorsFilterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @author guwenchang
 * @date 2019-04-29 17:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CorsFilterAutoConfiguration.class})
public @interface EnableCorsFilter {

}
