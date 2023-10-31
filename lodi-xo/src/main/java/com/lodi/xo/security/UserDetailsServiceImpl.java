package com.lodi.xo.security;

import com.lodi.common.core.utils.JwtUtils;
import com.lodi.common.model.entity.User;
import com.lodi.xo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

import static com.lodi.common.core.constant.StatusConstant.*;

/**
 * 用户认证 实现类
 * @author MaybeBin
 * @createDate 2023-10-24
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        if(user.getStatus() == OFF){
            throw new UsernameNotFoundException("用户已禁用");
        }
        if(user.getIsDelete() == DELETE ){
            throw new UsernameNotFoundException("用户已被删除");
        }

        return new SecurityUser(
                user.getId(),
                user.getUsername(),
                user.getUserPassword(),
                user.getStatus(),
                JwtUtils.generateExpireTime(),
                null);
    }
}
