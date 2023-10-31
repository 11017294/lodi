package com.lodi.xo.service;

import com.lodi.common.model.request.user.UserRegisterRequest;
import com.lodi.common.model.request.userAuth.LoginRequest;

/**
 * 用户认证 服务层
 * @author MaybeBin
 * @createDate 2023-10-31
 */
public interface AuthService {

    String login(LoginRequest loginRequest);

    Boolean register(UserRegisterRequest userRegisterRequest);

    Boolean logout();

}
