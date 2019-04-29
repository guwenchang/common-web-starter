package com.smart.starter.core.log.util;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.smart.starter.core.log.OpLogParam;
import com.smart.starter.core.security.context.SecurityContextHolder;
import com.smart.starter.core.security.jwt.User;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 日志组装
 *
 * @author guwenchang
 * @date 2019-04-29 11:49
 */
@UtilityClass
public class OpLogUtils {

    public OpLogParam getopLog() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        OpLogParam opLogParam = new OpLogParam();
        opLogParam.setCreateBy(Objects.requireNonNull(getUsername()));
        opLogParam.setRemoteAddr(ServletUtil.getClientIP(request));
        opLogParam.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        opLogParam.setMethod(request.getMethod());
        opLogParam.setUserAgent(request.getHeader("user-agent"));
        opLogParam.setParams(HttpUtil.toParams(request.getParameterMap()));
        return opLogParam;
    }

    /**
     * 获取用户名称
     *
     * @return username
     */
    private String getUsername() {
        User user = SecurityContextHolder.getContext();
        if (user == null) {
            return null;
        }
        return user.getUsername();
    }

}
