package com.lodi.xo.service.impl;

import com.lodi.common.config.redis.RedisService;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.holder.SecurityContextHolder;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.user.UserRegisterRequest;
import com.lodi.common.model.request.userAuth.LoginRequest;
import com.lodi.xo.security.TokenService;
import com.lodi.xo.service.AuthService;
import com.lodi.xo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

import static com.lodi.common.core.constant.StatusConstant.DELETE;
import static com.lodi.common.core.constant.StatusConstant.OFF;
import static com.lodi.common.core.constant.TokenConstants.LOGIN_TOKEN_KEY;
import static com.lodi.common.core.enums.ErrorCode.PARAMS_ERROR;

/**
 * 用户认证 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-10-31
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @Resource
    private RedisService redisService;

    public String login(LoginRequest loginRequest) {
        String loginType = loginRequest.getLoginType();
        User user = null;
        // todo 后续优化
        if ("password".equals(loginType)) {
            user = loginPassword(loginRequest);
        }
        return tokenService.createToken(user);
    }

    private User loginPassword(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // 获取用户信息
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (user.getStatus() == OFF) {
            throw new UsernameNotFoundException("用户已禁用");
        }
        if (user.getIsDelete() == DELETE) {
            throw new UsernameNotFoundException("用户已被删除");
        }
        boolean matches = passwordEncoder.matches(password, user.getUserPassword());
        if (!matches) {
            throw new UsernameNotFoundException("账号或密码错误");
        }
        // 验证通过，记录相应的登录成功日志

        return user;
    }

    public Boolean register(UserRegisterRequest registerRequest) {
        // 校验参数
        String userPassword = registerRequest.getPassword();
        String chenPassword = registerRequest.getCheckPassword();
        if (!Objects.equals(userPassword, chenPassword)) {
            throw new BusinessException("两次密码不一致", PARAMS_ERROR.getCode());
        }
        // 校验账号唯一性
        User oldUser = userService.getUserByUsername(registerRequest.getUsername());
        if (Objects.nonNull(oldUser)) {
            throw new BusinessException("账号已存在", PARAMS_ERROR.getCode());
        }
        // 对密码进行加密
        String encode = encodePassword(userPassword);
        // 转换对象
        User user = new User();
        BeanUtils.copyProperties(registerRequest, user);
        // 设置默认值
        user.setNickname(user.getUsername());
        user.setUserPassword(encode);
        // todo 临时设置
        user.setUserAvatar(user.getUsername());

        return userService.save(user);
    }

    public Boolean logout() {
        Long userId = SecurityContextHolder.getUserId();
        String redisKey = LOGIN_TOKEN_KEY + userId;
        return redisService.deleteObject(redisKey);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
