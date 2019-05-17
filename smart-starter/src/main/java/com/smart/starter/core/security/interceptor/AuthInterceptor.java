package com.smart.starter.core.security.interceptor;



import com.smart.starter.core.security.annotation.Action;
import com.smart.starter.core.security.annotation.Login;
import com.smart.starter.core.security.annotation.Permission;
import com.smart.starter.core.security.context.SecurityContextHolder;
import com.smart.starter.core.security.exception.SmartSecurityException;
import com.smart.starter.core.security.jwt.User;
import com.smart.starter.exeception.ApiException;
import com.smart.starter.exeception.BaseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 安全拦截器
 * @author guwenchang
 * @date 2019-04-22 16:28
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //默认都需要权限
            Login needLogin = handlerMethod.getBeanType().getAnnotation(Login.class);
            if (needLogin != null && needLogin.action() == Action.SKIP){

            }else {
                needLogin = handlerMethod.getMethodAnnotation(Login.class);
                if (needLogin == null || needLogin.action().equals(Action.NORMAL)) {
                    User user = SecurityContextHolder.getContext();
                    //登录校验
                    if (user.getUserId() == null) {
                        log.warn("need login:{},ParameterMap:{}",request.getRequestURI(),request.getParameterMap());
                        throw new ApiException(BaseError.UNAUTHORIZED);
                    }
                    //权限校验
                    boolean checkResult;
                    Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
                    if (permission != null) {
                        String perm = permission.value();
                        if (StringUtils.isEmpty(perm)){
                            checkResult = true;
                        }
                        List<String> userPerms = user.getPerms();
                        if (CollectionUtils.isEmpty(userPerms)) {
                            checkResult = false;
                        }else {
                            checkResult = userPerms.contains(perm);
                        }
                        if (!checkResult) {
                            log.warn("no permission，userPermsFromToken = {}, perm = {}", userPerms, perm);
                            throw new ApiException(BaseError.FORBIDDEN);
                        }
                    }
                }
            }
            return super.preHandle(request, response, handler);
        }
        return true;
    }
}

