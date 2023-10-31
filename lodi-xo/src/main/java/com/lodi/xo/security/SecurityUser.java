package com.lodi.xo.security;

import com.lodi.common.model.system.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static com.lodi.common.core.constant.StatusConstant.ON;

/**
 * 用户详细信息 实现类
 * @author MaybeBin
 * @createDate 2023-10-24
 */
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String username;
    private final String password;
    private final Integer status;

    /**
     * 权限
     */
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * 到期时间
     */
    private Long expireTime;

    public SecurityUser(
            Long id,
            String username,
            String password,
            Integer status,
            Long expireTime,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.expireTime = expireTime;
        this.authorities = authorities;
    }

    public SecurityUser(LoginUser loginUser) {
        this.id = loginUser.getId();
        this.username = loginUser.getUsername();
        this.password = "";
        this.status = loginUser.getStatus();
        this.expireTime = loginUser.getExpireTime();
        this.authorities = loginUser.getAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        long currentTime = System.currentTimeMillis();
        return currentTime < expireTime;
    }

    @Override
    public boolean isEnabled() {
        return status == ON;
    }

    public Long getId() {
        return id;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
