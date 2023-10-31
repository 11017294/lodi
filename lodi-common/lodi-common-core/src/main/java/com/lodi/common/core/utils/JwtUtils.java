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

    public static String createToken(Map<String, Object> claims) {
        // todo 是否给token增加过期时间
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
    }

    public static Long getUserId(String token) {
        Claims claims = parseToken(token);
        return claims.get(USER_ID, Long.class);
    }

    public static String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims.get(USERNAME, String.class);
    }

    public static Long getExpireTime(String token) {
        Claims claims = parseToken(token);
        return claims.get(EXPIRE_TIME, Long.class);
    }

    public static Long generateExpireTime() {
        return generateExpireTime(DURATION_IN_SECONDS);
    }

    public static Long generateExpireTime(Long durationInSeconds) {
        return System.currentTimeMillis() + durationInSeconds * 1000;
    }

}
