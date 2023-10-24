package com.lodi.common.security.handler;

import com.lodi.common.core.utils.ServletUtils;
import com.lodi.common.core.web.domain.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 认证成功处理器
 * @author MaybeBin
 * @createDate 2023-10-20
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result<String> result = Result.success("登录成功");
        ServletUtils.webResponseWriter(response, result);
    }
}
