package com.lodi.common.core.utils;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.web.domain.Result;

/**
 * 返回结果工具类
 * @author MaybeBin
 * @createDate 2023-09-19
 */
public class ResultUtil {

    /**
     * 成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(0, data, "ok");
    }

    /**
     * 失败
     * @param errorCode
     * @return
     */
    public static Result error(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), null, errorCode.getMessage());
    }

    /**
     * 失败
     * @param errorCode
     * @param message
     * @return
     */
    public static Result error(ErrorCode errorCode, String message) {
        return new Result<>(errorCode.getCode(), null, message);
    }

    /**
     * 失败
     * @param code
     * @param message
     * @return
     */
    public static Result error(int code, String message) {
        return new Result<>(code, null, message);
    }

}
