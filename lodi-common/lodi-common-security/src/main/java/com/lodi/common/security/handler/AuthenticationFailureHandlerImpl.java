package com.lodi.common.security.handler;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.utils.ServletUtils;
import com.lodi.common.core.web.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 * @author MaybeBin
 * @createDate 2023-10-20
 */
@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error(exception.getMessage());

        String message = exception.getMessage();
        if(exception instanceof UsernameNotFoundException){
            message = exception.getMessage();
        } else if(exception instanceof BadCredentialsException){
            message = "密码错误!";
        }

        Result result = Result.error(ErrorCode.SYSTEM_ERROR, message);
        ServletUtils.webResponseWriter(response, result);
    }
}
