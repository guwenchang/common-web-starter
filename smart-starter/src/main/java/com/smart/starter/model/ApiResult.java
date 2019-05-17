package com.smart.starter.model;


import com.alibaba.fastjson.JSON;
import com.smart.starter.exeception.IError;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 通用返回结果分装
 * @author guwenchang
 * @date 2019-05-17
 */

public class ApiResult<T> {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    public static final String SUCCESS_MSG = "success";
    public static final String ERROR_MSG = "error";

    /**
     * 返回结果状态码
     */
    @ApiModelProperty("响应状态 0 成功，1 失败")
    @Setter
    @Getter
    private int status;
    /**
     * 返回结果状态信息
     */
    @ApiModelProperty("响应消息")
    @Setter
    @Getter
    private String msg;
    /**
     * 返回结果的数据
     */
    @ApiModelProperty("响应数据")
    @Setter
    @Getter
    private T data;


    public static <T> ApiResult<T> success() {
        return new ApiResult();
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(SUCCESS, SUCCESS_MSG, data);
    }

    public static <T> ApiResult<T> error() {
        return new ApiResult<>(ERROR, ERROR_MSG, null);
    }

    public static <T> ApiResult<T> error(int code, String msg) {
        return new ApiResult<>(code, msg, null);
    }

    public static <T> ApiResult<T> error(IError error, String msg) {
        return new ApiResult<>(error.getCode(), msg, null);
    }

    public static <T> ApiResult<T> error(String msg) {
        return new ApiResult<>(ERROR, msg, null);
    }

    public ApiResult() {
        this.status = SUCCESS;
        this.msg = SUCCESS_MSG;
        this.data = null;
    }

    public ApiResult(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResult{" + "status=" + status + ", msg='" + msg + '\'' + ", data=" + JSON.toJSONString(data) + "}";
    }

}
