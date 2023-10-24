package com.lodi.common.security.handler;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.utils.ServletUtils;
import com.lodi.common.core.web.domain.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名用户无权限访问的异常处理器
 * @author MaybeBin
 * @createDate 2023-10-20
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<String> msg = Result.error(ErrorCode.SYSTEM_ERROR, "您还未登录，无法访问该资源！");
        ServletUtils.webResponseWriter(response, msg);
    }
}
