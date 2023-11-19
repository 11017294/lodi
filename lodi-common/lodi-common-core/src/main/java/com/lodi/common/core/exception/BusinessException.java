package com.lodi.common.core.exception;

import com.lodi.common.core.enums.ErrorCode;

/**
 * 业务异常
 *
 * @author MaybeBin
 * @createDate 2023-09-19
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int code;

    public BusinessException(Exception e) {
        super(e);
        code = ErrorCode.SYSTEM_ERROR.getCode();
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
