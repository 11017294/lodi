package com.lodi.web.controller;

import com.lodi.api.RemoteFileService;
import com.lodi.common.core.constant.FileDirectoryConstant;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.domain.Result;
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
    private RemoteFileService remoteFileService;

    @Operation(summary = "上传图片")
    @PostMapping("upload")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(ErrorCode.FILE_IS_EMPTY);
        }
        return remoteFileService.upload(file, FileDirectoryConstant.DEFAULT);
    }

    @Operation(summary = "删除图片")
    @DeleteMapping("delete")
    @Parameter(name = "fileName", description = "文件名", required = true)
    public void deleteImage(@RequestParam("fileName") String fileName) {
        if (StringUtils.isBlank(fileName)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // todo 判断是否当前用户或管理员
        Result<Integer> result = remoteFileService.delete(fileName, FileDirectoryConstant.DEFAULT);
        if(result.getData() == -1){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除图片失败");
        }
    }

}
