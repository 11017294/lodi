package com.lodi.xo.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.lodi.common.core.constant.ContextConstant;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.system.LoginUser;
import com.lodi.common.core.utils.StrUtils;
import com.lodi.common.model.convert.user.UserConvert;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.user.UserRegisterRequest;
import com.lodi.common.model.request.userAuth.LoginRequest;
import com.lodi.common.model.vo.UserVO;
import com.lodi.common.redis.utils.RedisUtils;
import com.lodi.common.satoken.utils.LoginHelper;
import com.lodi.xo.service.AuthService;
import com.lodi.xo.service.UserService;
import com.lodi.xo.utils.RabbitMQUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.lodi.common.core.constant.StatusConstant.DELETE;
import static com.lodi.common.core.constant.StatusConstant.OFF;
import static com.lodi.common.core.enums.ErrorCode.*;

/**
 * 用户认证 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-10-31
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final RabbitMQUtils rabbitMQUtils;

    public SaTokenInfo login(LoginRequest loginRequest) {
        String loginType = loginRequest.getLoginType();
        LoginUser loginUser = null;
        // todo 后续优化
        if ("password".equals(loginType)) {
            loginUser = loginPassword(loginRequest);
        }
        StpUtil.login(loginUser.getId());
        StpUtil.getTokenSession().set(ContextConstant.LOGIN_USER, loginUser);
        return StpUtil.getTokenInfo();
    }

    private LoginUser loginPassword(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // 获取用户信息
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new BusinessException(PARAMS_ERROR, "用户不存在");
        }
        if (user.getStatus() == OFF) {
            throw new BusinessException(SYSTEM_ERROR, "用户已禁用");
        }
        if (user.getIsDelete() == DELETE) {
            throw new BusinessException(SYSTEM_ERROR, "用户已被删除");
        }
        boolean matches = BCrypt.checkpw(password, user.getUserPassword());
        if (!matches) {
            throw new BusinessException(SYSTEM_ERROR, "账号或密码错误");
        }
        // 验证通过，记录相应的登录成功日志
        LoginUser login = UserConvert.INSTANCE.toLogin(user);
        login.setRoles(new HashSet<>(Arrays.asList(user.getUserRole())));

        return login;
    }

    public Boolean register(UserRegisterRequest registerRequest) {
        // 校验验证码
        useSmsCode(registerRequest.getEmail(), registerRequest.getCode());

        // 校验参数
        String userPassword = registerRequest.getPassword();
        String confirmPassword = registerRequest.getConfirmPassword();
        if (!Objects.equals(userPassword, confirmPassword)) {
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
        User user = UserConvert.INSTANCE.toEntity(registerRequest);

        // 设置默认值
        user.setNickname(user.getUsername());
        user.setUserPassword(encode);
        // todo 临时设置
        user.setUserAvatar(user.getUsername());

        return userService.save(user);
    }

    public void logout() {
        StpUtil.logout();
    }

    private static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    @Override
    public void sendEmailCode(String email) {
        String randomCode = StrUtils.randomCode();
        rabbitMQUtils.sendAuthCodeEmail(email, randomCode);
        RedisUtils.setCacheObject(String.format("emailCode:%s", email), randomCode, 300L, TimeUnit.SECONDS);
    }

    public void useSmsCode(String email, String code) {
        if (StringUtils.isBlank(code)) {
            throw new BusinessException(PARAMS_ERROR);
        }
        String key = String.format("emailCode:%s", email);
        String randomCode = RedisUtils.getCacheObject(key);
        // 缓存验证码不存在
        if (StringUtils.isBlank(randomCode)) {
            throw new BusinessException(EMAIL_CODE_NOT_FOUND);
        }
        // 验证码无效
        if (!code.equals(randomCode)) {
            throw new BusinessException(INVALID_EMAIL_CODE);
        }
        // 验证完清除掉code
        RedisUtils.deleteObject(key);
    }

    @Override
    public UserVO getUserInfo() {
        Long userId = LoginHelper.getUserId();
        User user = userService.getById(userId);
        if (user == null) {
            throw new BusinessException(SYSTEM_ERROR, "用户不存在");
        }
        if (user.getStatus() == OFF) {
            throw new BusinessException(SYSTEM_ERROR, "用户已禁用");
        }
        if (user.getIsDelete() == DELETE) {
            throw new BusinessException(SYSTEM_ERROR, "用户已被删除");
        }
        return UserConvert.INSTANCE.toVO(user);
    }
}
