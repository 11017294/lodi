package com.lodi.common.satoken.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.system.LoginUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

import static com.lodi.common.core.constant.ContextConstant.*;

/**
 * 获取当前线程变量中的 用户id、用户名称、Token等信息
 *
 * @author MaybeBin
 * @createDate 2024-05-28
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {

    /**
     * 获取用户id
     *
     * @return 用户id
     */
    public static Long getUserId() {
        Long userId = (Long) SaHolder.getStorage().get(USER_ID);
        if (Objects.isNull(userId)) {
            userId = StpUtil.getLoginId(0L);
            SaHolder.getStorage().set(USER_ID, userId);
        }
        return userId;
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        String username = SaHolder.getStorage().getString(USERNAME);
        if (Objects.isNull(username)) {
            username = getLoginUser().getUsername();
            SaHolder.getStorage().set(USERNAME, username);
        }
        return username;
    }

    /**
     * 基于 Token-Session 获取登录用户
     *
     * @return 登录用户
     */
    public static LoginUser getLoginUser() {
        LoginUser user = (LoginUser) SaHolder.getStorage().get(LOGIN_USER);
        if (Objects.isNull(user)) {
            user = (LoginUser) StpUtil.getTokenSession().get(LOGIN_USER);
            SaHolder.getStorage().set(LOGIN_USER, user);
        }
        return user;
    }

    /**
     * 获取登录用户基于token
     *
     * @param token 令牌
     * @return 登录用户
     */
    public static LoginUser getLoginUser(String token) {
        return (LoginUser) StpUtil.getTokenSessionByToken(token).get(LOGIN_USER);
    }

    /**
     * 检查是否 登录用户或管理员
     *
     * @param userId 用户id
     */
    public static void checkLoginUserOrAdmin(Long userId) {
        if (isLoginUserOrAdmin(userId)) {
            return;
        }
        throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
    }

    /**
     * 是否 登录用户或管理员
     *
     * @param userId 用户id
     * @return 是：true，否：false
     */
    public static boolean isLoginUserOrAdmin(Long userId) {
        return isLoginUser(userId) || isAdmin();
    }

    /**
     * 是否 登录用户
     *
     * @param userId 用户id
     * @return 是：true，否：false
     */
    public static boolean isLoginUser(Long userId) {
        return Objects.equals(getUserId(), userId);
    }

    /**
     * 是否 管理员
     *
     * @return 是：true，否：false
     */
    public static boolean isAdmin() {
        Set<String> roles = getLoginUser().getRoles();
        return roles.contains("admin");
    }

}
