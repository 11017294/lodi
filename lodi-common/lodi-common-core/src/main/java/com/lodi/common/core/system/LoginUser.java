package com.lodi.common.core.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录用户信息
 *
 * @author MaybeBin
 * @createDate 2023-10-31
 */
@Data
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 角色
     */
    private Set<String> roles;

    /**
     * 权限
     */
    private Set<String> permissions;

    /**
     * 到期时间
     */
    private Long expireTime;

}
