package com.lodi.xo.service;

import com.lodi.common.model.request.user.UserRegisterRequest;
import com.lodi.common.model.request.userAuth.LoginRequest;

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
    String login(LoginRequest loginRequest);

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
    Boolean logout();

}
