package com.lodi.common.core.constant;

/**
 * 令牌常量
 * @author MaybeBin
 * @createDate 2023-10-30
 */
public interface TokenConstants {

    String TOKEN_HEADER = "Authorization";

    String TOKEN_PREFIX = "Bearer ";

    String LOGIN_TOKEN_KEY = "login_tokens:";

    String TOKEN_SECRET = "abcdefghijklmnopqrstuvwxyz";

    Long DURATION_IN_SECONDS = 60 * 60 * 24 * 7 * 30L;

    Long TOKEN_REFRESH_THRESHOLD_IN_SECONDS = 60 * 60 * 24 * 5L;

}
