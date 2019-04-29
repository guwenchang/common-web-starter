package com.smart.starter.core.security.filter;


import com.smart.starter.core.log.util.RequestUtils;
import com.smart.starter.core.security.context.SecurityContextHolder;
import com.smart.starter.core.security.jwt.User;
import com.smart.starter.core.security.jwt.UserOperator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 安全上下文过滤器
 * @author guwenchang
 * @date 2019-04-24 10:45
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class SmartSecurityContextHolderFilter extends GenericFilterBean {
	private final UserOperator userOperator;

	@Override
	@SneakyThrows
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		log.debug("request:{}", RequestUtils.dump(httpServletRequest));
		User user = userOperator.getUser();
		log.debug("获取用户信息:{}", user);
		if (user != null){
			SecurityContextHolder.setContext(user);
		}else {
			SecurityContextHolder.createEmptyContext();
		}
		filterChain.doFilter(request, response);
		SecurityContextHolder.clearContext();
	}
}
