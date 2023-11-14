package com.lodi.admin.controller;

import com.lodi.common.core.web.domain.Result;
import com.lodi.common.model.request.user.UserRegisterRequest;
import com.lodi.common.model.request.userAuth.LoginRequest;
import com.lodi.xo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MaybeBin
 * @createDate 2023-09-22
 */
@Tag(name = "用户认证")
@RestController
@RequestMapping("auth")
public class AuthController {

    @Resource
    private AuthService authService;

    /**
      {
          "username":"admin",
          "password":"123456",
          "loginType":"password"
      }
     **/
    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return Result.success(token);
    }

    @Operation(summary = "用户注册")
    @PostMapping("register")
    public Result<Boolean> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        Boolean flag = authService.register(userRegisterRequest);
        return Result.success(flag);
    }

    @Operation(summary = "用户注销")
    @PostMapping("logout")
    public Result<Boolean> logout() {
        Boolean flag = authService.logout();
        return Result.success(flag);
    }

}
