package com.lodi.xo.service.impl;

import com.lodi.common.config.redis.RedisService;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.user.UserRegisterRequest;
import com.lodi.common.model.request.userAuth.LoginRequest;
import com.lodi.xo.security.SecurityUser;
import com.lodi.xo.security.TokenService;
import com.lodi.xo.service.AuthService;
import com.lodi.xo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

import static com.lodi.common.core.constant.TokenConstants.LOGIN_TOKEN_KEY;
import static com.lodi.common.core.enums.ErrorCode.PARAMS_ERROR;

/**
 * @author MaybeBin
 * @createDate 2023-10-31
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @Resource
    private RedisService redisService;

    public String login(LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // <2> 用户验证
        Authentication authentication;
        // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
        authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        // <2.2> 验证通过，记录相应的登录成功日志

        // <3> 生成 Token
        SecurityUser loginUser = (SecurityUser) authentication.getPrincipal();

        return tokenService.createToken(loginUser);
    }

    public Boolean register(UserRegisterRequest userRegisterRequest){
        // 校验参数
        String userPassword = userRegisterRequest.getPassword();
        String chenPassword = userRegisterRequest.getCheckPassword();
        if(!Objects.equals(userPassword, chenPassword)){
            throw new BusinessException("两次密码不一致", PARAMS_ERROR.getCode());
        }
        // 校验账号唯一性
        User oldUser = userService.getUserByUsername(userRegisterRequest.getUsername());
        if(Objects.nonNull(oldUser)){
            throw new BusinessException("账号已存在", PARAMS_ERROR.getCode());
        }
        // 对密码进行加密
        String encode = encodePassword(userPassword);
        // 转换对象
        User user = new User();
        BeanUtils.copyProperties(userRegisterRequest, user);
        // 设置默认值
        user.setNickname(user.getUsername());
        user.setUserPassword(encode);
        // todo 临时设置
        user.setUserAvatar(user.getUsername());

        return userService.save(user);
    }

    public Boolean logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser loginUser = (SecurityUser) authentication.getPrincipal();
        Long userId = loginUser.getId();
        String redisKey = LOGIN_TOKEN_KEY + userId;
        return redisService.deleteObject(redisKey);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
