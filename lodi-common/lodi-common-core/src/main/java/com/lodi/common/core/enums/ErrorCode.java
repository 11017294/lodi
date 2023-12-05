package com.lodi.common.core.enums;

/**
 * 错误码枚举
 *
 * @author MaybeBin
 * @createDate 2023-09-19
 */
public enum ErrorCode {

    SUCCESS(0, "ok"),

    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    EMAIL_CODE_NOT_FOUND(40200, "邮箱验证码不存在"),
    INVALID_EMAIL_CODE(40201, "邮箱验证码无效"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    RESPONSE_PACK_ERROR(50002, "response封装错误"),
    FILE_IS_EMPTY(50003, "文件为空"),
    OPERATION_ERROR(50001, "操作失败");

    private final int code;

    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
