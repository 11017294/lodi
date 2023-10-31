package com.lodi.xo.security;

import com.lodi.common.config.redis.RedisService;
import com.lodi.common.core.utils.JwtUtils;
import com.lodi.common.model.system.LoginUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.lodi.common.core.constant.CommonConstant.*;
import static com.lodi.common.core.constant.TokenConstants.*;

/**
 * 令牌服务
 * @author MaybeBin
 * @createDate 2023-10-27
 */
@Component
public class TokenService {

    @Resource
    private RedisService redisService;

    /**
     * 创建令牌
     *
     * @param securityUser 用户信息
     * @return 令牌
     */
    public String createToken(SecurityUser securityUser) {
        // <1> 设置 LoginUser 的用户唯一标识
//        String key = IdUtils.fastUUID();

        LoginUser loginUser = new LoginUser();
        loginUser.setId(securityUser.getId());
        loginUser.setUsername(securityUser.getUsername());
        loginUser.setAuthorities(securityUser.getAuthorities());
        loginUser.setExpireTime(securityUser.getExpireTime());

        // <2> 设置用户终端相关的信息，包括 IP、城市、浏览器、操作系统
        setUserAgent(loginUser);

        // <3> 记录缓存
        refreshToken(loginUser);

        // <4> 生成 JWT 的 Token
        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, loginUser.getId());
        claims.put(USERNAME, loginUser.getUsername());
        claims.put(EXPIRE_TIME, loginUser.getExpireTime());

        return JwtUtils.createToken(claims);
    }

    private void setUserAgent(LoginUser securityUser) {
        securityUser.setExpireTime(JwtUtils.generateExpireTime());
    }

    public void verifyToken(LoginUser securityUser) {
        Long expireTime = securityUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if(expireTime - currentTime < TOKEN_REFRESH_THRESHOLD_IN_SECONDS){
            refreshToken(securityUser);
        }
    }

    public LoginUser getLoginUser(String token) {
        Long userId = JwtUtils.getUserId(token);
        return redisService.getCacheObject(getTokenKey(userId.toString()));
    }

    public void refreshToken(LoginUser loginUser) {
        redisService.setCacheObject(getTokenKey(loginUser.getId().toString()), loginUser, DURATION_IN_SECONDS, TimeUnit.SECONDS);
    }

    private String getTokenKey(String userId){
        return LOGIN_TOKEN_KEY + userId;
    }

}
