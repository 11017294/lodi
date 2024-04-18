package com.lodi.web.controller;

import com.lodi.api.RemoteFileService;
import com.lodi.common.core.constant.FileDirectoryConstant;
import com.lodi.common.core.domain.Result;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.convert.user.UserConvert;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.user.UserSelfUpdateRequest;
import com.lodi.common.model.vo.UserInfoVO;
import com.lodi.xo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.lodi.common.core.constant.StatusConstant.DELETE;
import static com.lodi.common.core.constant.StatusConstant.OFF;

/**
 * @author MaybeBin
 * @createDate 2023-09-22
 */
@Tag(name = "用户信息相关接口")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RemoteFileService remoteFileService;

    @Operation(summary = "更新用户个人信息")
    @PutMapping("updateUserInfo")
    public Result<Boolean> updateUser(@RequestBody @Validated UserSelfUpdateRequest updateRequest) {
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

    @Operation(summary = "获取用户信息")
    @GetMapping("get")
    public Result<UserInfoVO> getUser(@ParameterObject @Validated IdRequest idRequest) {
        User user = userService.getById(idRequest.getId());
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if (user.getStatus() == OFF) {
            throw new BusinessException("用户已禁用", ErrorCode.SYSTEM_ERROR.getCode());
        }
        if (user.getIsDelete() == DELETE) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return Result.success(UserConvert.INSTANCE.toInfoVO(user));
    }
}
