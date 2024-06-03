package com.lodi.common.web.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        log.error("[{}] {}", request.getMethod(), getUrl(request), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<String> handleRuntimeException(HttpServletRequest request, RuntimeException e) {
        log.error("[{}] {}", request.getMethod(), getUrl(request), e);
        return Result.error(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage());
    }

    /**
     * 拦截参数验证异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result validExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        FieldError firstFieldError = CollectionUtils.firstElement(e.getBindingResult().getFieldErrors());
        String exceptionStr = Optional.ofNullable(firstFieldError).map(FieldError::getDefaultMessage).orElse(EMPTY);
        log.error("[{}] {} {}", request.getMethod(), getUrl(request), exceptionStr, e);
        return Result.error(ErrorCode.PARAMS_ERROR.getCode(), exceptionStr);
    }

    /**
     * 拦截参数验证异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleBindException(HttpServletRequest request, BindException e) {
        FieldError firstFieldError = CollectionUtils.firstElement(e.getBindingResult().getFieldErrors());
        String exceptionStr = Optional.ofNullable(firstFieldError).map(FieldError::getDefaultMessage).orElse(EMPTY);
        log.error("[{}] {} {}", request.getMethod(), getUrl(request), exceptionStr, e);
        return Result.error(ErrorCode.PARAMS_ERROR.getCode(), exceptionStr);
    }

    /**
     * 凭证无效异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Result handleNotLoginException(HttpServletRequest request, NotLoginException e) {
        log.error("[{}] {}", request.getMethod(), getUrl(request), e);
        return Result.error(ErrorCode.NOT_LOGIN_ERROR.getCode(), e.getMessage());
    }

    /**
     * 无角色异常
     */
    @ExceptionHandler(NotRoleException.class)
    public Result handleNotRoleException(HttpServletRequest request, NotRoleException e) {
        log.error("[{}] {}", request.getMethod(), getUrl(request), e);
        return Result.error(ErrorCode.NO_AUTH_ERROR.getCode(), e.getMessage());
    }

    /**
     * 无权限异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public Result handleNotPermissionException(HttpServletRequest request, NotPermissionException e) {
        log.error("[{}] {}", request.getMethod(), getUrl(request), e);
        return Result.error(ErrorCode.NO_AUTH_ERROR.getCode(), e.getMessage());
    }

    private String getUrl(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getQueryString())) {
            return request.getRequestURL().toString();
        }
        return request.getRequestURL().toString() + "?" + request.getQueryString();
    }
}
