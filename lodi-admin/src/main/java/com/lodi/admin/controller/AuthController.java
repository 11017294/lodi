package com.lodi.admin.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.lodi.common.core.domain.Result;
import com.lodi.common.model.request.user.UserRegisterRequest;
import com.lodi.common.model.request.userAuth.LoginRequest;
import com.lodi.common.model.vo.UserVO;
import com.lodi.xo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<SaTokenInfo> login(@RequestBody @Validated LoginRequest loginRequest) {
        SaTokenInfo tokenInfo = authService.login(loginRequest);
        return Result.success(tokenInfo);
    }

    @Operation(summary = "用户注册")
    @PostMapping("register")
    public Result<Boolean> register(@RequestBody @Validated UserRegisterRequest registerRequest) {
        Boolean flag = authService.register(registerRequest);
        return Result.success(flag);
    }

    @Operation(summary = "发送邮箱验证码")
    @PostMapping("sendEmailCode")
    @Parameter(name = "email", description = "邮箱", required = true)
    public Result<Boolean> sendEmailCode(@RequestParam("email") String email) {
        authService.sendEmailCode(email);
        return Result.success(true);
    }

    @Operation(summary = "用户注销")
    @PostMapping("logout")
    public Result<Boolean> logout() {
        authService.logout();
        return Result.success(true);
    }

    @GetMapping("get")
    @Operation(summary = "获得用户基本信息")
    public Result<UserVO> getUserInfo() {
        return Result.success(authService.getUserInfo());
    }

}
