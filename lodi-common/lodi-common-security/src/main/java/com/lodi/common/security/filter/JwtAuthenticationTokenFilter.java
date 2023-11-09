package com.lodi.common.security.filter;

import com.google.gson.Gson;
import com.lodi.common.core.utils.ServletUtils;
import com.lodi.common.model.system.LoginUser;
import com.lodi.xo.security.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.lodi.common.core.constant.ContextConstant.LOGIN_USER;

/**
 * JWT认证过滤器 【验证token有效性】
 *
 * @author：MaybeBin
 * @date: 2022-02-17 15:00
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 得到请求头信息
        String login = request.getHeader(LOGIN_USER);

        if(StringUtils.isNotBlank(login)) {
            String valueEncode = ServletUtils.urlDecode(login);
            LoginUser loginUser = new Gson().fromJson(valueEncode, LoginUser.class);
            if(Objects.nonNull(loginUser)) {
                SecurityUser securityUser = new SecurityUser(loginUser);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        securityUser, null, securityUser.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                        request));
                // 以后可以security中取得SecurityUser信息
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}


