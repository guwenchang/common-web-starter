package com.smart.starter.core.security.jwt;


import com.smart.starter.core.security.constants.ConstantsSecurity;
import com.smart.starter.core.security.exception.SmartSecurityException;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户操作
 * @author guwenchang
 * @date 2019-04-22 15:52
 */
@Slf4j
@AllArgsConstructor
public class UserOperator {
    private static final String SMART_SECURITY_REQ_ATTR_USER = "smart-security-user";
    private static final int SEVEN = 7;

    private final JwtOperator jwtOperator;

    public User getUser() {
        try {
            HttpServletRequest request = getRequest();
            String token = getTokenFromRequest(request);
            if (StringUtils.isEmpty(token)){
                return null;
            }
            Boolean isValid = jwtOperator.validateToken(token);
            if (!isValid) {
                return null;
            }

            Object userInReq = request.getAttribute(SMART_SECURITY_REQ_ATTR_USER);
            if (userInReq != null) {
                return (User) userInReq;
            }
            User user = getUserFromToken(token);
            request.setAttribute(SMART_SECURITY_REQ_ATTR_USER, user);
            return user;
        } catch (Exception e) {
            log.info("发生异常", e);
            throw new SmartSecurityException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private User getUserFromToken(String token) {
        // 从token中获取user
        Claims claims = jwtOperator.getClaimsFromToken(token);
        Object perms = claims.get(JwtOperator.PERMS);
        Object userId = claims.get(JwtOperator.USER_ID);
        Object username = claims.get(JwtOperator.USERNAME);

        return User.builder()
                .userId((Integer) userId)
                .username((String) username)
                .perms((List<String>) perms)
                .build();
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        //在请求参数中找
        String tokenValue = request.getParameter(ConstantsSecurity.TOKEN_PARAM_KEY);
        if (!StringUtils.isEmpty(tokenValue)) {
            return tokenValue;
        }
        //在cookie中取
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (ConstantsSecurity.TOKEN_COOKIE_KEY.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        //如果cookie中没有，则从header中取
        String header = request.getHeader(ConstantsSecurity.AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(header)) {
            return null;
        }
        if (!header.startsWith(ConstantsSecurity.BEARER)) {
            throw new SmartSecurityException("token should start with 'Bearer ' ");
        }
        if (header.length() <= SEVEN) {
            throw new SmartSecurityException("Token Invalided，length <= 7");
        }
        return header.substring(SEVEN);
    }


    private static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if ((requestAttributes == null)) {
            throw new SmartSecurityException("requestAttributes is null");
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }
}
