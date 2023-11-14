package com.lodi.common.security.auth;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.holder.SecurityContextHolder;
import com.lodi.common.core.system.LoginUser;
import com.lodi.common.security.annotation.RequiresPermissions;
import com.lodi.common.security.annotation.RequiresRoles;
import com.lodi.common.security.enums.Logical;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.PatternMatchUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Token 权限验证，逻辑实现类
 *
 * @author MaybeBin
 * @createDate 2023-11-14
 */
public class AuthLogic {

    /***
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";

    /**
     * 管理员角色权限标识
     */
    private static final String SUPER_ADMIN = "admin";

    // region 角色校验

    /**
     * 根据注解鉴权
     *
     * @param requiresRoles
     */
    public void checkRole(RequiresRoles requiresRoles) {
        Logical logical = requiresRoles.logical();
        if (logical == Logical.AND) {
            checkRoleAnd(requiresRoles.value());
        } else {
            checkRoleOr(requiresRoles.value());
        }
    }

    /**
     * 验证用户是否含有指定的全部角色
     *
     * @param requiresRoles
     */
    private void checkRoleAnd(String[] requiresRoles) {
        Set<String> roles = getRoles();
        for (String requiresRole : requiresRoles) {
            if (!hasRole(roles, requiresRole)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
    }

    /**
     * 验证用户是否含有指定的任意角色
     *
     * @param requiresRoles
     */
    private void checkRoleOr(String[] requiresRoles) {
        Set<String> roles = getRoles();
        for (String requiresRole : requiresRoles) {
            if (hasRole(roles, requiresRole)) {
                return;
            }
        }
        throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
    }

    /**
     * 判断是否包含角色
     *
     * @param roles
     * @param role
     * @return
     */
    public boolean hasRole(Collection<String> roles, String role) {
        return roles.stream().filter(StringUtils::isNotBlank).anyMatch(
                x -> SUPER_ADMIN.equals(x) || x.equals(role)
        );
    }

    // endregion

    // region 权限校验

    /**
     * 根据注解鉴权
     *
     * @param requiresPermissions
     */
    public void checkPermission(RequiresPermissions requiresPermissions) {
        Logical logical = requiresPermissions.logical();
        if (logical == Logical.AND) {
            checkPermissionAnd(requiresPermissions.value());
        } else {
            checkPermissionOr(requiresPermissions.value());
        }
    }

    /**
     * 验证用户是否含有指定的全部权限
     *
     * @param requiresPermissions
     */
    private void checkPermissionAnd(String[] requiresPermissions) {
        Set<String> permissions = getPermissions();
        for (String requiresPermission : requiresPermissions) {
            if (!hasPermission(permissions, requiresPermission)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
    }

    /**
     * 验证用户是否含有指定的任意权限
     *
     * @param requiresPermissions
     */
    private void checkPermissionOr(String[] requiresPermissions) {
        Set<String> permissions = getPermissions();
        for (String requiresPermission : requiresPermissions) {
            if (hasPermission(permissions, requiresPermission)) {
                return;
            }
        }
        throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
    }

    /**
     * 判断是否包含权限
     *
     * @param permissions
     * @param permission
     * @return
     */
    public boolean hasPermission(Collection<String> permissions, String permission) {
        return permissions.stream().filter(StringUtils::isNotBlank).anyMatch(
                x -> ALL_PERMISSION.contains(x) || PatternMatchUtils.simpleMatch(x, permission)
        );
    }

    // endregion

    // region 登录用户相关信息

    /**
     * 获取登录用户的角色列表
     *
     * @return
     */
    private Set<String> getRoles() {
        LoginUser loginUser = getLoginUser();
        Set<String> roles = loginUser.getRoles();
        return roles == null ? new HashSet<>() : roles;
    }

    /**
     * 获取登录用户的权限列表
     *
     * @return
     */
    private Set<String> getPermissions() {
        LoginUser loginUser = getLoginUser();
        Set<String> permissions = loginUser.getPermissions();
        return permissions == null ? new HashSet<>() : permissions;
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public LoginUser getLoginUser() {
        LoginUser loginUser = SecurityContextHolder.getLoginUser();
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return loginUser;
    }

    // endregion

}
