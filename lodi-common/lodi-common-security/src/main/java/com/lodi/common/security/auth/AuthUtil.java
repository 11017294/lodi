package com.lodi.common.security.auth;

import com.lodi.common.security.annotation.RequiresPermissions;
import com.lodi.common.security.annotation.RequiresRoles;

/**
 * Token 权限验证工具类
 *
 * @author MaybeBin
 * @createDate 2023-11-14
 */
public class AuthUtil {

    /**
     * 底层的 AuthLogic 对象
     */
    public static AuthLogic authLogic = new AuthLogic();

    public static void checkRole(RequiresRoles requiresRoles) {
        authLogic.checkRole(requiresRoles);
    }

    public static void checkPermission(RequiresPermissions requiresPermissions) {
        authLogic.checkPermission(requiresPermissions);
    }
}
