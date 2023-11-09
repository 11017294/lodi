package com.lodi.common.security.interceptor;

import com.google.gson.Gson;
import com.lodi.common.core.holder.SecurityContextHolder;
import com.lodi.common.core.system.LoginUser;
import com.lodi.common.core.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lodi.common.core.constant.ContextConstant.LOGIN_USER;

/**
 * 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 *
 * @author MaybeBin
 * @createDate 2023-11-14
 */
public class HeaderInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginUserStr = ServletUtils.getHeader(request, LOGIN_USER);
        if (StringUtils.isNotBlank(loginUserStr)) {
            LoginUser loginUser = new Gson().fromJson(loginUserStr, LoginUser.class);

            SecurityContextHolder.setUserId(loginUser.getId());
            SecurityContextHolder.setUsername(loginUser.getUsername());
            SecurityContextHolder.setLoginUser(loginUser);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SecurityContextHolder.remove();
    }
}
