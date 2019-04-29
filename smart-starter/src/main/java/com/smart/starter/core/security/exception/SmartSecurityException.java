package com.smart.starter.core.security.exception;

/**
 * 安全校验异常
 * @author guwenchang
 * @date 2019-04-22 14:51
 */
public class SmartSecurityException extends RuntimeException {

    private int code;

    public SmartSecurityException(Throwable cause) {
        super(cause);
    }

    public SmartSecurityException(String message) {
        super(message);
    }

    public SmartSecurityException(int code,String message) {
        super(message);
    }

    public SmartSecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}
