package com.lodi.common.security.filter;

import com.lodi.common.model.system.LoginUser;
import com.lodi.xo.security.SecurityUser;
import com.lodi.xo.security.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.lodi.common.core.constant.TokenConstants.TOKEN_HEADER;

/**
 * JWT认证过滤器 【验证token有效性】
 *
 * @author：MaybeBin
 * @date: 2022-02-17 15:00
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 得到请求头信息authorization信息(token)
        String token = request.getHeader(TOKEN_HEADER);

        // 请求头 'Authorization': token
        if (StringUtils.hasText(token)) {
            log.info("前端传递的token为: {}", token);

            LoginUser loginUser = tokenService.getLoginUser(token);
            if(Objects.nonNull(loginUser)){
                // 验证token是否过期
                tokenService.verifyToken(loginUser);
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


