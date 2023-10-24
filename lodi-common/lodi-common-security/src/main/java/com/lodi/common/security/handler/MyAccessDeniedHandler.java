package com.lodi.common.security.handler;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.utils.ServletUtils;
import com.lodi.common.core.web.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录用户没有权限访问资源处理器
 * @author MaybeBin
 * @createDate 2023-10-20
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<String> msg = Result.error(ErrorCode.SYSTEM_ERROR, "您没有权限执行此操作！");
        ServletUtils.webResponseWriter(response, msg);
    }
}
