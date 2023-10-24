package com.lodi.common.security.handler;

import com.lodi.common.core.utils.ServletUtils;
import com.lodi.common.core.web.domain.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功处理器
 * @author MaybeBin
 * @createDate 2023-10-23
 */
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result<String> result = Result.success("退出成功");
        ServletUtils.webResponseWriter(response, result);
    }
}
