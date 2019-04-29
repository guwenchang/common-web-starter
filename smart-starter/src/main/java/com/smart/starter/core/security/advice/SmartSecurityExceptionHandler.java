package com.smart.starter.core.security.advice;

import com.smart.starter.core.security.exception.SmartSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 安全异常处理
 * @author guwenchang
 * @date 2019-04-22 18:47
 */
@Slf4j
@ControllerAdvice
public class SmartSecurityExceptionHandler {
    /**
     * Smart Security相关异常
     *
     * @param e e
     * @return 发生异常时的返回
     */
    @ExceptionHandler(value = {SmartSecurityException.class})
    @ResponseBody
    public ResponseEntity<String> error(SmartSecurityException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
