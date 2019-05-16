package com.smart.starter.annotation;

import com.smart.starter.autoconfigure.i18n.I18nAutoConfiguration;
import com.smart.starter.core.i18n.LocaleMessage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 国际化配置
 * @author guwenchang
 * @date 2019-04-29 18:02
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({I18nAutoConfiguration.class, LocaleMessage.class})
public @interface EnableI18n {

}
