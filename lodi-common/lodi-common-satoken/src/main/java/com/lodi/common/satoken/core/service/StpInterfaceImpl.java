package com.lodi.common.satoken.core.service;

import cn.dev33.satoken.stp.StpInterface;
import com.lodi.common.satoken.utils.LoginHelper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义权限验证接口扩展
 *
 * @author MaybeBin
 * @createDate 2024-05-30
 */
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Set<String> permissions = LoginHelper.getLoginUser().getPermissions();
        return permissions.stream().collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Set<String> roles = LoginHelper.getLoginUser().getRoles();
        return roles.stream().collect(Collectors.toList());
    }

}
