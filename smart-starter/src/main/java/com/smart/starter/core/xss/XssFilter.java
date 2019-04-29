package com.smart.starter.core.xss;


import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * xss 过滤器
 * @author guwenchang
 * @date 2019-04-29 17:31
 */
public class XssFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final XssRequestWrapper requestWrapper = new XssRequestWrapper(request);
        filterChain.doFilter(requestWrapper, response);
    }

}
