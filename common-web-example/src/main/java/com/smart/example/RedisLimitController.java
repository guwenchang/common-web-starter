package com.smart.example;


import com.smart.starter.core.redis.RedisParam;
import com.smart.starter.core.redis.limit.RedisLimit;
import com.smart.starter.core.security.annotation.Action;
import com.smart.starter.core.security.annotation.Login;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 * @since 2018/12/24 0024
 */
@RestController
@RequestMapping("/limit")
public class RedisLimitController {


    @GetMapping
    @Login(action = Action.SKIP)
    @RedisLimit(prefix = "test")
    public String test1(@RedisParam(name = "a") String a, @RedisParam String b) {
        return a + b;
    }


}
