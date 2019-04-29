package com.smart.example;


import com.smart.starter.core.log.annotation.OpLog;
import com.smart.starter.core.security.annotation.Action;
import com.smart.starter.core.security.annotation.Login;
import com.smart.starter.core.security.annotation.Permission;
import com.smart.starter.core.security.jwt.JwtOperator;
import com.smart.starter.core.security.jwt.User;
import com.smart.starter.core.security.jwt.UserOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 *
 * @author guwenchang
 * @date 2019-04-22 17:50
 */
@RequestMapping
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
    private final UserOperator userOperator;
    private final JwtOperator operator;

    /**
     *
     * @return 用户信息
     */
    @GetMapping("/user")
    @Permission("user")
    @OpLog("user")
    public User user() {
        return userOperator.getUser();
    }

    /**
     *
     * @return
     */
    @GetMapping("/admin")
    @Permission("admin")
    public String admin() {
        return "你有admin权限";
    }

    /**
     * 模拟登录，颁发token
     *
     * @return token字符串
     */
    @GetMapping("/login")
    @Login(action = Action.SKIP)
    public String loginReturnToken() {
        User user = User.builder()
                .userId(1)
                .username("张三")
                .perms(Arrays.asList("user"))
                .build();
        return operator.generateToken(user);
    }
}