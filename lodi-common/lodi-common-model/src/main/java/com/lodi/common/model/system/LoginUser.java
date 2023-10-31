package com.lodi.common.model.system;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * 登录用户信息
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
     * 权限
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 到期时间
     */
    private Long expireTime;


}
