package com.smart.example;


import com.smart.example.param.I18nParam;
import com.smart.starter.core.i18n.LocaleMessage;
import com.smart.starter.core.security.annotation.Action;
import com.smart.starter.core.security.annotation.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Levin
 * @since 2018/12/20 0020
 */
@RestController
@RequestMapping("/i18n")
@RequiredArgsConstructor
public class I18nController {
    private final LocaleMessage localeMessage;

    @GetMapping("get/{id}")
    @Login(action = Action.SKIP)
    public String index(@PathVariable long id) {
        return localeMessage.getMessage("welcome");
    }

    @PostMapping("post")
    @Login(action = Action.SKIP)
    public I18nParam index(@Valid I18nParam i18) {
        return i18;
    }


}
