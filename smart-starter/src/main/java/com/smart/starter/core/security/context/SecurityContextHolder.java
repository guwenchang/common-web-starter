package com.smart.starter.core.security.context;

import com.smart.starter.core.security.jwt.User;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

/**
 * @author guwenchang
 * @date 2019-04-23 20:36
 */
@UtilityClass
public class SecurityContextHolder {

    private static final ThreadLocal<User> contextHolder = new ThreadLocal<>();

    public void clearContext() {
        contextHolder.remove();
    }

    public User getContext() {
        User ctx = contextHolder.get();
        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }
        return ctx;
    }

    public void setContext(User context) {
        Assert.notNull(context, "Only non-null context instances are permitted");
        contextHolder.set(context);
    }

    public User createEmptyContext() {
        return new User();
    }


}
