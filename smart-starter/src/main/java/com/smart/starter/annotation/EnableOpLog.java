package com.smart.starter.annotation;

import com.smart.starter.autoconfigure.log.OpLogAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启操作日志记录
 * @author guwenchang
 * @date 2019-04-22 15:59
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({OpLogAutoConfiguration.class})
public @interface EnableOpLog {
}
