package com.smart.example;

import com.smart.example.param.TestParam;
import com.smart.starter.core.security.annotation.Action;
import com.smart.starter.core.security.annotation.Login;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 * @since 2018/12/25 0025
 */
@RestController
@RequestMapping("/xss")
public class XssController {


    @PostMapping
    @Login(action = Action.SKIP)
    public String testXss(@RequestParam String param) {
        return param;
    }


}
