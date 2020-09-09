package com.kanavi.response.api;

/**
 * 枚举了一些常用API操作码
 * Created by thl
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    DEPT_NOT_FOUND_EXCEPTION(30003, "部门不存在"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    ARITHMETIC_EXCEPTION(30001, "算数异常"),
    USER_NOT_FOUND_EXCEPTION(30002, "用户不存在");
    private Integer code;
    private String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
