package com.lodi.gateway.filter;

import com.google.gson.Gson;
import com.lodi.common.config.redis.RedisService;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.system.LoginUser;
import com.lodi.common.core.utils.JwtUtils;
import com.lodi.common.core.utils.ServletUtils;
import com.lodi.common.core.utils.StrUtils;
import com.lodi.gateway.config.IgnoreWhiteProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.lodi.common.core.constant.ContextConstant.LOGIN_USER;
import static com.lodi.common.core.constant.TokenConstants.*;

/**
 * 网关认证过滤器
 *
 * @author MaybeBin
 * @createDate 2023-11-06
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisService redisService;
    @Resource
    private IgnoreWhiteProperties ignoreWhite;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 1 黑名单过滤

        // 2 清除请求头，防止攻击干扰
        ServerHttpRequest.Builder mutate = request.mutate();
        removeHeader(mutate, LOGIN_USER);

        // 3 todo 白名单放行
        String url = request.getPath().toString();
        if (StrUtils.matches(url, ignoreWhite.getWhites())) {
            return chain.filter(exchange);
        }

        // 4 认证
        // 4.1 验证token
        String token = request.getHeaders().getFirst(TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            return unauthorizedResponse(exchange, "该资源需要登录访问，请登录后访问");
        }
        // 4.2 解析token
        Long userId = JwtUtils.getUserId(token);
        if (Objects.isNull(userId)) {
            return unauthorizedResponse(exchange, "凭证已失效，请重新登录");
        }
        String tokenKey = getTokenKey(String.valueOf(userId));
        // 4.3 从redis拿到登录用户的信息
        LoginUser loginUser = redisService.getCacheObject(tokenKey);
        // 4.4 判断凭证是否有效
        if (Objects.isNull(loginUser)) {
            return unauthorizedResponse(exchange, "凭证已失效，请重新登录");
        }
        // 验证token有效期，若满足要求，则刷新token有效期
        verifyToken(loginUser);

        // 5 将用户信息放入请求头
        addHeader(mutate, LOGIN_USER, loginUser);
        return chain.filter(exchange);
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (value == null) {
            return;
        }
        Gson gson = new Gson();
        String valueStr = gson.toJson(value);
        mutate.header(name, ServletUtils.urlEncode(valueStr));
    }

    /**
     * 清除请求头信息
     *
     * @param mutate
     * @param name
     */
    private void removeHeader(ServerHttpRequest.Builder mutate, String name) {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    /**
     * 验证token有效期，若满足要求，则刷新token有效期
     *
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser) {
        Long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        // 有效时长小于5天，则刷新token有效期
        if (expireTime - currentTime < TOKEN_REFRESH_THRESHOLD_IN_SECONDS) {
            // 刷新token
            String tokenKey = getTokenKey(loginUser.getId().toString());
            loginUser.setExpireTime(JwtUtils.generateExpireTime());
            redisService.setCacheObject(tokenKey, loginUser, DURATION_IN_SECONDS, TimeUnit.SECONDS);
        }
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        return ServletUtils.webFluxResponseWriter(exchange.getResponse(), msg, ErrorCode.NO_AUTH_ERROR.getCode());
    }

    private String getTokenKey(String userId) {
        return LOGIN_TOKEN_KEY + userId;
    }

    @Override
    public int getOrder() {
        return -100;
    }

}
