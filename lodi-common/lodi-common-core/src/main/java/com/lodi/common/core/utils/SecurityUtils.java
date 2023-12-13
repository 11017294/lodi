package com.lodi.common.core.utils;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.holder.SecurityContextHolder;
import com.lodi.common.core.system.LoginUser;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * 安全服务工具类
 *
 * @author MaybeBin
 * @createDate 2023-12-12
 */
public class SecurityUtils {

    // todo 临时使用，待优化，结合AuthLogic类
    private static final String SUPER_ADMIN = "admin";

    public static boolean hasRole(Collection<String> roles) {
        return roles.stream().filter(StringUtils::isNotBlank).anyMatch(x -> SUPER_ADMIN.equals(x));
    }

    public static void isCurrentUserOrAdmin(Long id) {
        if(isCurrentUser(id) || isAdmin()){
            return;
        }
        throw new BusinessException(ErrorCode.OPERATION_ERROR);
    }


    public static boolean isCurrentUser(Long id) {
        Long userId = SecurityContextHolder.getUserId();
        return Objects.equals(userId, id);
    }

    public static boolean isAdmin() {
        LoginUser loginUser = SecurityContextHolder.getLoginUser();
        Set<String> roles = loginUser.getRoles();
       return hasRole(roles);
    }

}
