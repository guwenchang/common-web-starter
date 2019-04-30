package com.smart.starter.annotation;

import com.smart.starter.autoconfigure.swagger.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @author guwenchang
 * @date 2019-04-30
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableSwagger2Doc {


}
