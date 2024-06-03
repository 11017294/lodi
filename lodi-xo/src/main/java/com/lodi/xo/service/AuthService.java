package com.lodi.xo.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.lodi.common.model.request.user.UserRegisterRequest;
import com.lodi.common.model.request.userAuth.LoginRequest;
import com.lodi.common.model.vo.UserVO;

/**
 * 用户认证 服务层
 *
 * @author MaybeBin
 * @createDate 2023-10-31
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    SaTokenInfo login(LoginRequest loginRequest);

    /**
     * 注册
     *
     * @param registerRequest
     * @return
     */
    Boolean register(UserRegisterRequest registerRequest);

    /**
     * 注销
     *
     * @return
     */
    void logout();

    /**
     * 发送邮箱验证码
     *
     * @param email
     */
    void sendEmailCode(String email);

    /**
     * 获取用户信息
     *
     * @return
     */
    UserVO getUserInfo();

}
