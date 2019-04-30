package com.smart.example;


import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 通用返回结果
 *
 * @author xiaobai
 * @date 2017/10/29 15:00
 **/

public class ApiResult<T> {

    public static final int SUCCESS = 0;
    public static final int NO_LOGIN = -1;
    public static final int NO_PERMISSION = -2;
    public static final int VALIDATE = 1;
    public static final int ERROR = 2;

    public static final String SUCCESS_MSG = "success";
    public static final String ERROR_MSG = "出错了！";
    public static final String NO_LOGIN_MSG = "登录失效，请重新登录！";
    public static final String NO_PERMISSION_MSG = "没有权限！";
    public static final String VALIDATE_MSG = "参数错误！";

    /**
     * 返回结果状态码
     */
    @ApiModelProperty("响应状态 0 成功，-1 登录失效，1 参数校验错误，2 服务器处理错误")
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


    public static <T> ApiResult<T> error() {
        return new ApiResult<>(ERROR, ERROR_MSG, null);
    }


    public static <T> ApiResult<T> noLogin() {
        return new ApiResult<>(NO_LOGIN, NO_LOGIN_MSG, null);
    }

    public static <T> ApiResult<T> noPermission() {
        return new ApiResult<>(NO_PERMISSION, NO_PERMISSION_MSG, null);
    }

    public static <T> ApiResult<T> validate() {
        return new ApiResult<>(VALIDATE, VALIDATE_MSG, null);
    }
    public static <T> ApiResult<T> validate(String msg) {
        return new ApiResult<>(VALIDATE, msg, null);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(SUCCESS, SUCCESS_MSG, data);
    }

    public static <T> ApiResult<T> error(int code, String msg) {
        return new ApiResult<>(code, msg, null);
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
