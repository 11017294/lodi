package com.lodi.common.core.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;

import static com.lodi.common.core.constant.CommonConstant.*;
import static com.lodi.common.core.constant.TokenConstants.*;

/**
 * jwt 工具类
 * @author MaybeBin
 * @createDate 2023-10-30
 */
public class JwtUtils {

    /**
     * 生成token
     * @param claims
     * @return token
     */
    public static String createToken(Map<String, Object> claims) {
        // todo 是否给token增加过期时间
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();
    }

    /**
     * 解析token
     * @param token
     * @return 数据声明
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
    }

    /**
     * 从令牌中获取用户id
     * @param token
     * @return 用户id
     */
    public static Long getUserId(String token) {
        Claims claims = parseToken(token);
        return getUserId(claims);
    }

    /**
     * 从数据声明中获取用户id
     * @param claims
     * @return 用户id
     */
    public static Long getUserId(Claims claims) {
        return claims.get(USER_ID, Long.class);
    }

    /**
     * 从令牌中获取用户名
     * @param token
     * @return 用户名
     */
    public static String getUsername(String token) {
        Claims claims = parseToken(token);
        return getUsername(claims);
    }

    /**
     * 从数据声明中获取用户名
     * @param claims
     * @return 用户名
     */
    public static String getUsername(Claims claims) {
        return claims.get(USERNAME, String.class);
    }

    /**
     * 从令牌中获取过期时间
     * @param token
     * @return 过期时间的时间戳
     */
    public static Long getExpireTime(String token) {
        Claims claims = parseToken(token);
        return getExpireTime(claims);
    }

    /**
     * 从数据声明中获取过期时间
     * @param claims
     * @return 过期时间的时间戳
     */
    public static Long getExpireTime(Claims claims) {
        return claims.get(EXPIRE_TIME, Long.class);
    }

    /**
     * 生成过期时间
     * @return 过期时间的时间戳
     */
    public static Long generateExpireTime() {
        return generateExpireTime(DURATION_IN_SECONDS);
    }

    /**
     * 生成过期时间
     * @param durationInSeconds token 持续时间（秒）
     * @return 过期时间的时间戳
     */
    public static Long generateExpireTime(Long durationInSeconds) {
        return System.currentTimeMillis() + durationInSeconds * 1000;
    }

}
