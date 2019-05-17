package com.smart.starter.exeception;

import lombok.Getter;


/**
 * api 异常封装
 * @author guwenchang
 * @date 2019-05-17
 */
public class ApiException extends RuntimeException {

	@Getter
	private final IError error;


	public ApiException(IError error) {
		super(error.getMessage());
		this.error = error;
	}

	public ApiException(IError error, Throwable cause) {
		super(cause);
		this.error = error;
	}

}
