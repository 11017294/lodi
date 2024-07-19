package com.lodi.file.controller;

import com.lodi.common.core.domain.Result;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.file.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件请求处理
 *
 * @author MaybeBin
 * @createDate 2023-11-20
 */
@Slf4j
@RestController
@AllArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("upload")
    public Result<String> upload(MultipartFile file, String fileDirectory) {
        try {
            return Result.success(fileService.upload(file.getBytes(), fileDirectory));
        } catch (IOException e) {
            log.error("上传文件失败", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传文件失败");
        }
    }

    @DeleteMapping("delete")
    public Result<Integer> delete(String fileName, String fileDirectory) {
        return Result.success(fileService.delete(fileName, fileDirectory));
    }

}
