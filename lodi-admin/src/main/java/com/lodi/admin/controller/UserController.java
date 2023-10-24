package com.lodi.admin.controller;

import com.lodi.common.core.utils.RedisUtil;
import com.lodi.common.core.web.domain.Result;
import com.lodi.common.model.entity.User;
import com.lodi.xo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MaybeBin
 * @createDate 2023-09-22
 */
@Tag(name = "用户管理", description = "用户管理")
@RestController
@RequestMapping
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    RedisUtil redisUtil;

    @Operation(summary = "获取用户列表")
    @GetMapping("/listUser")
    public Result<List<User>> listUser(){
        redisUtil.set("user", "user");
        System.out.println(redisUtil.get("user"));
        return Result.success(userService.list());
    }

    @Operation(summary = "获取用户列表")
    @PostMapping("/auth/login")
    public Result<String> login(String username, String password){
        return Result.success("login");
    }

}
