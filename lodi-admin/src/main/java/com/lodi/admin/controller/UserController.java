package com.lodi.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.web.domain.Result;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.user.UserPageRequest;
import com.lodi.common.model.request.user.UserQueryRequest;
import com.lodi.common.model.request.user.UserUpdateRequest;
import com.lodi.common.model.vo.UserVO;
import com.lodi.xo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MaybeBin
 * @createDate 2023-09-22
 */
@Tag(name = "用户管理", description = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "获取用户列表")
    @GetMapping("list")
    public Result<List<User>> listUser(UserQueryRequest userQueryRequest){
        return Result.success(userService.listUser(userQueryRequest));
    }

    @Operation(summary = "获取用户分页")
    @GetMapping("page")
    public Result<Page<User>> getUserPage(UserPageRequest userPageRequest){
        return Result.success(userService.getUserPage(userPageRequest));
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("get")
    public Result<UserVO> getUser(IdRequest idRequest){
        User user = userService.getById(idRequest.getId());
        if(user == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    @Operation(summary = "删除用户")
    @PostMapping("delete")
    public Result<Boolean> deleteUser(@RequestBody IdRequest idRequest){
        return Result.success(userService.removeById(idRequest.getId()));
    }

    @Operation(summary = "更新用户")
    @PostMapping(value = "update")
    public Result<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        return Result.success(userService.updateById(user));
    }

}
