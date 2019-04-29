package com.smart.example;


import com.smart.example.param.I18nParam;
import com.smart.starter.core.i18n.LocaleMessage;
import com.smart.starter.core.security.annotation.Action;
import com.smart.starter.core.security.annotation.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Levin
 * @since 2018/12/20 0020
 */
@RestController
@RequestMapping("/i18n")
public class I18nController {


    private final LocaleMessage localeMessage;

    @Autowired
    public I18nController(LocaleMessage localeMessage) {
        this.localeMessage = localeMessage;
    }


    @GetMapping
    @Login(action = Action.SKIP)
    public String index() {
        return localeMessage.getMessage("welcome.error");
    }

    @PostMapping
    @Login(action = Action.SKIP)
    public I18nParam index(@Valid I18nParam i18) {
        return i18;
    }


}
