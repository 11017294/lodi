package com.lodi.web.controller;

import com.lodi.common.core.domain.Result;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.xo.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author MaybeBin
 * @createDate 2023-12-08
 */
@Slf4j
@Tag(name = "文件相关接口")
@RestController
@RequestMapping("file")
public class FileController {

    @Resource
    private FileService fileService;

    @Operation(summary = "头像上传")
    @PostMapping(value = "avatar")
    public Result<String> uploadAvatar(MultipartFile avatarFile) {
        if (avatarFile.isEmpty()) {
            throw new BusinessException(ErrorCode.FILE_IS_EMPTY);
        }
        return Result.success(fileService.uploadAvatar(avatarFile));
    }

    @Operation(summary = "上传图片")
    @PostMapping("upload")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            throw new BusinessException(ErrorCode.FILE_IS_EMPTY);
        }
        return Result.success(fileService.uploadMarkdownImage(imageFile));
    }

    @Operation(summary = "删除图片")
    @DeleteMapping("delete")
    @Parameter(name = "fileName", description = "文件名", required = true)
    public Result<Boolean> deleteImage(@RequestParam("fileName") String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return Result.success(fileService.deleteImageByPath(filePath));
    }

}
