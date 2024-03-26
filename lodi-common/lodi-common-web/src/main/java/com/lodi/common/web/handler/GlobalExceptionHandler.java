package com.lodi.common.web.handler;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.lodi.common.core.constant.CommonConstant.EMPTY;

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
    public Result<String> handleBusinessException(HttpServletRequest request, BusinessException e) {
        log.error("[{}] {} [BusinessException] {}", request.getMethod(), getUrl(request), e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<String> handleRuntimeException(HttpServletRequest request, RuntimeException e) {
        log.error("[{}] {} [RuntimeException] {}", request.getMethod(), getUrl(request), e.getMessage());
        return Result.error(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage());
    }

    /**
     * 拦截参数验证异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result validExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException ex) {
        FieldError firstFieldError = CollectionUtils.firstElement(ex.getBindingResult().getFieldErrors());
        String exceptionStr = Optional.ofNullable(firstFieldError).map(FieldError::getDefaultMessage).orElse(EMPTY);
        log.error("[{}] {} [MethodArgumentNotValidException] {}", request.getMethod(), getUrl(request), exceptionStr);
        return Result.error(ErrorCode.PARAMS_ERROR.getCode(), exceptionStr);
    }

    /**
     * 拦截参数验证异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleBindException(HttpServletRequest request, BindException ex) {
        FieldError firstFieldError = CollectionUtils.firstElement(ex.getBindingResult().getFieldErrors());
        String exceptionStr = Optional.ofNullable(firstFieldError).map(FieldError::getDefaultMessage).orElse(EMPTY);
        log.error("[{}] {} [BindException] {}", request.getMethod(), getUrl(request), exceptionStr);
        return Result.error(ErrorCode.PARAMS_ERROR.getCode(), exceptionStr);
    }

    /**
     * 用户不存在异常
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result<String> handleUsernameNotFoundException(HttpServletRequest request, UsernameNotFoundException e) {
        log.error("[{}] {} [UsernameNotFoundException] {}", request.getMethod(), getUrl(request), e.getMessage());
        return Result.error(ErrorCode.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    /**
     * 凭证无效异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Result<String> handleBadCredentialsException(HttpServletRequest request, BadCredentialsException e) {
        log.error("[{}] {} [BadCredentialsException] {}", request.getMethod(), getUrl(request), e.getMessage());
        return Result.error(ErrorCode.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    private String getUrl(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getQueryString())) {
            return request.getRequestURL().toString();
        }
        return request.getRequestURL().toString() + "?" + request.getQueryString();
    }
}
