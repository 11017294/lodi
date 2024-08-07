package com.lodi.admin.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.api.RemoteFileService;
import com.lodi.common.core.constant.FileDirectoryConstant;
import com.lodi.common.core.domain.Result;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.convert.user.UserConvert;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.user.UserPageRequest;
import com.lodi.common.model.request.user.UserQueryRequest;
import com.lodi.common.model.request.user.UserUpdateRequest;
import com.lodi.common.model.vo.UserVO;
import com.lodi.xo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author MaybeBin
 * @createDate 2023-09-22
 */
@Tag(name = "用户管理", description = "用户管理")
@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final RemoteFileService remoteFileService;

    @SaCheckRole("admin")
    @Operation(summary = "获取用户列表")
    @GetMapping("list")
    public Result<List<UserVO>> getUserList(@ParameterObject @Validated UserQueryRequest queryRequest) {
        List<User> users = userService.listUser(queryRequest);
        return Result.success(UserConvert.INSTANCE.toVO(users));
    }

    @Operation(summary = "获取用户分页")
    @GetMapping("page")
    public Result<Page<UserVO>> getUserPage(@ParameterObject @Validated UserPageRequest pageRequest) {
        Page<User> userPage = userService.getUserPage(pageRequest);
        return Result.success(UserConvert.INSTANCE.toVO(userPage));
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("get")
    public Result<UserVO> getUser(@ParameterObject @Validated IdRequest idRequest) {
        User user = userService.getById(idRequest.getId());
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return Result.success(UserConvert.INSTANCE.toVO(user));
    }

    @SaCheckRole("admin")
    @Operation(summary = "删除用户")
    @DeleteMapping("delete")
    public Result<Boolean> deleteUser(@RequestBody @Validated IdRequest idRequest) {
        return Result.success(userService.removeById(idRequest.getId()));
    }

    @Operation(summary = "更新用户")
    @PutMapping("update")
    public Result<Boolean> updateUser(@RequestBody @Validated UserUpdateRequest updateRequest) {
        User user = UserConvert.INSTANCE.toEntity(updateRequest);
        return Result.success(userService.updateById(user));
    }

    @Operation(summary = "上传头像")
    @PostMapping("uploadAvatar")
    public Result<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(ErrorCode.FILE_IS_EMPTY);
        }
        return remoteFileService.upload(file, FileDirectoryConstant.DEFAULT);
    }

}
