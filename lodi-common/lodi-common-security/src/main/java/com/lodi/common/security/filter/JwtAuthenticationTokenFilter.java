package com.lodi.common.security.filter;

import com.lodi.common.model.entity.User;
import com.lodi.xo.security.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        // 得到请求头信息authorization信息(token)
        String token = request.getHeader("Authorization");

        token = "aa";
        // 请求头 'Authorization': token
        if (StringUtils.hasText(token)) {
            log.error("前端传递的token为: {}", token);

            // 通过用户名加载SpringSecurity用户
            UserDetails userDetails = new SecurityUser(new User());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                    request));
            // 以后可以security中取得SecurityUser信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}


