package com.smart.starter.autoconfigure.i18n;

import cn.hutool.core.util.StrUtil;
import com.smart.starter.constants.Constants;
import com.smart.starter.core.i18n.I18nProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 *
 * @author guwenchang
 * @date 2019-04-29 17:53
 */
@Configuration
@EnableConfigurationProperties(value = {I18nProperties.class})
public class I18nAutoConfiguration {

    private final I18nProperties properties;

    @Autowired
    public I18nAutoConfiguration(I18nProperties properties) {
        this.properties = properties;
    }

    private ResourceBundleMessageSource getMessageSource() throws Exception {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding(StrUtil.blankToDefault(properties.getDefaultEncoding(), Constants.DEFAULT_CHARSET));
        bundleMessageSource.setBasenames(properties.getBaseNames());
        return bundleMessageSource;
    }

    @Bean
    public Validator getValidator() throws Exception {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(getMessageSource());
        return validator;
    }
}
