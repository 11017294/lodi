package com.lodi.common.core.domain;

import com.lodi.common.core.enums.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @author MaybeBin
 * @createDate 2023-09-19
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private T data;
    private String message;

    public Result() {

    }

    private Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static Result error(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), null, errorCode.getMessage());
    }

    /**
     * 失败
     *
     * @param errorCode
     * @param message
     * @return
     */
    public static Result error(ErrorCode errorCode, String message) {
        return new Result<>(errorCode.getCode(), null, message);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @return
     */
    public static Result error(int code, String message) {
        return new Result<>(code, null, message);
    }

}
