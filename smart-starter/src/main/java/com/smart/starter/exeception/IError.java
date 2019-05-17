package com.smart.starter.exeception;

/**
 *
 * @author guwenchang
 * @date 2019-05-17
 */
public interface IError {


    /**
     * @return 错误码
     */
    int getCode();

    /**
     * @return 错误信息
     */
    String getMessage();
}
