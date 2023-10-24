package com.lodi.common.security.handler;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.utils.ServletUtils;
import com.lodi.common.core.web.domain.Result;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无效会话处理器
 * @author MaybeBin
 * @createDate 2023-10-23
 */
public class InvalidSessionStrategyImpl implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Result<String> result = Result.error(ErrorCode.SYSTEM_ERROR,"会话已过期，请重新登录");
        ServletUtils.webResponseWriter(response, result);
    }
}
