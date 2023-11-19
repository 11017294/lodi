package com.lodi.common.core.handler;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.web.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author MaybeBin
 * @createDate 2023-09-19
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException e) {
        log.error("BusinessException", e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<String> handleRuntimeException(RuntimeException e) {
        log.error("RuntimeException", e);
        return Result.error(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage());
    }

    /**
     * 用户不存在异常
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error("UsernameNotFoundException", e);
        return Result.error(ErrorCode.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    /**
     * 凭证无效异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Result<String> handleBadCredentialsException(BadCredentialsException e) {
        log.error("BadCredentialsException", e);
        return Result.error(ErrorCode.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
