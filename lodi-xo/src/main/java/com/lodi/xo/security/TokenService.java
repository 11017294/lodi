package com.lodi.xo.security;

import com.lodi.common.config.redis.RedisService;
import com.lodi.common.core.utils.JwtUtils;
import com.lodi.common.model.entity.User;
import com.lodi.common.core.system.LoginUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.lodi.common.core.constant.CommonConstant.*;
import static com.lodi.common.core.constant.TokenConstants.*;

/**
 * 令牌服务
 *
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
     * @param user 用户信息
     * @return 令牌
     */
    public String createToken(User user) {
        LoginUser loginUser = new LoginUser();

        loginUser.setId(user.getId());
        loginUser.setUsername(user.getUsername());
        loginUser.setStatus(user.getStatus());
        loginUser.setRoles(new HashSet<>(Arrays.asList(user.getUserRole())));
        loginUser.setExpireTime(JwtUtils.generateExpireTime());

        // <1> 设置用户终端相关的信息，包括 IP、城市、浏览器、操作系统
        setUserAgent(loginUser);

        // <2> 记录缓存
        refreshToken(loginUser);

        // <3> 生成 JWT 的 Token
        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, loginUser.getId());
        claims.put(USERNAME, loginUser.getUsername());
        claims.put(EXPIRE_TIME, loginUser.getExpireTime());

        return JwtUtils.createToken(claims);
    }

    /**
     * 设置用户相关信息
     *
     * @param securityUser
     */
    private void setUserAgent(LoginUser securityUser) {
        securityUser.setExpireTime(JwtUtils.generateExpireTime());
    }

    /**
     * 验证token是否过期
     *
     * @param securityUser
     */
    public void verifyToken(LoginUser securityUser) {
        Long expireTime = securityUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime < TOKEN_REFRESH_THRESHOLD_IN_SECONDS) {
            refreshToken(securityUser);
        }
    }

    /**
     * 获取登录用户
     *
     * @param token
     * @return
     */
    public LoginUser getLoginUser(String token) {
        Long userId = JwtUtils.getUserId(token);
        return redisService.getCacheObject(getTokenKey(userId.toString()));
    }

    /**
     * 刷新token
     *
     * @param loginUser
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setExpireTime(JwtUtils.generateExpireTime());
        redisService.setCacheObject(getTokenKey(loginUser.getId().toString()), loginUser, DURATION_IN_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * 拼接token key
     *
     * @param userId
     * @return
     */
    private String getTokenKey(String userId) {
        return LOGIN_TOKEN_KEY + userId;
    }

}
