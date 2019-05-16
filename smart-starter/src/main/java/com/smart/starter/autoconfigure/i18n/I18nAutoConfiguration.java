package com.smart.starter.autoconfigure.i18n;

import cn.hutool.core.util.StrUtil;
import com.smart.starter.constants.Constants;
import com.smart.starter.core.i18n.I18nProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;


/**
 * 国际化配置
 * @author guwenchang
 * @date 2019-04-29 17:53
 */
@Configuration
@EnableConfigurationProperties(value = {I18nProperties.class})
@RequiredArgsConstructor
public class I18nAutoConfiguration implements WebMvcConfigurer {

    private final I18nProperties properties;

    private static final int COOKIE_LOCALE_AGE = 604800;
    private static final String COOKIE_LOCALE_NAME = "locale";


    /**
     * 用于校验信息的国际化文件
     * @return
     */
    private ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding(StrUtil.blankToDefault(properties.getDefaultEncoding(), Constants.DEFAULT_CHARSET));
        bundleMessageSource.setBasenames(properties.getBaseNames());
        return bundleMessageSource;
    }

    @Bean
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        //自定义校验文件
        validator.setValidationMessageSource(getMessageSource());
        return validator;
    }


    /**
     * locale 解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName(COOKIE_LOCALE_NAME);
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        localeResolver.setCookieMaxAge(COOKIE_LOCALE_AGE);
        return localeResolver;
    }

    /**
     * locale 的全局拦截器，来处理locale
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        // 设置请求地址的参数,默认为：locale
        localeChangeInterceptor.setParamName(LocaleChangeInterceptor.DEFAULT_PARAM_NAME);
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
