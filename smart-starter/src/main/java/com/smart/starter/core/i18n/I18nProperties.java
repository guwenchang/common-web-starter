package com.smart.starter.core.i18n;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author guwenchang
 * @date 2019-04-29 17:52
 */
@Data
@ConfigurationProperties("smart.i18n")
public class I18nProperties {

    /**
     * 默认 UTF-8
     */
    private String defaultEncoding;


    /**
     * 语言文件路径
     */
    private String[] baseNames;

}
