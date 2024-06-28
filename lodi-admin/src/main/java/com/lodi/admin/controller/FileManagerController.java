package com.lodi.admin.controller;

import com.lodi.common.model.entity.FileManager;
import com.lodi.xo.service.FileManagerService;
import com.lodi.common.model.request.fileManager.FileManagerAddRequest;
import com.lodi.common.model.request.fileManager.FileManagerUpdateRequest;
import com.lodi.common.model.request.fileManager.FileManagerPageRequest;
import com.lodi.common.model.convert.fileManager.FileManagerConvert;
import com.lodi.common.model.vo.FileManagerVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.domain.Result;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.request.IdRequest;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import static com.lodi.common.core.enums.ErrorCode.NOT_FOUND_ERROR;

/**
 * @author MaybeBin
 * @createDate 2024-06-27
 */
@Tag(name = "文件管理", description = "文件管理")
@RestController
@RequestMapping("fileManager")
public class FileManagerController {

    @Resource
    private FileManagerService fileManagerService;

    @Operation(summary = "获取文件管理分页")
    @GetMapping("page")
    public Result<Page<FileManagerVO>> getFileManagerPage(@ParameterObject FileManagerPageRequest pageRequest) {
        Page<FileManager> fileManagerPage = fileManagerService.getFileManagerPage(pageRequest);
        return Result.success(FileManagerConvert.INSTANCE.toVO(fileManagerPage));
    }

    @Operation(summary = "获取文件管理")
    @GetMapping("get")
    public Result<FileManagerVO> getFileManager(@ParameterObject IdRequest request) {
        FileManager fileManager = fileManagerService.getById(request.getId());
        if (fileManager == null) {
            throw new BusinessException(NOT_FOUND_ERROR);
        }
        return Result.success(FileManagerConvert.INSTANCE.toVO(fileManager));
    }

    @Operation(summary = "新增文件管理")
    @PostMapping("add")
    public Result<Boolean> addFileManager(@RequestBody FileManagerAddRequest addRequest) {
        return Result.success(fileManagerService.insertFileManager(addRequest));
    }

    @Operation(summary = "更新文件管理")
    @PutMapping("update")
    public Result<Boolean> updateFileManager(@RequestBody FileManagerUpdateRequest updateRequest) {
        return Result.success(fileManagerService.updateFileManager(updateRequest));
    }

    @Operation(summary = "删除文件管理")
    @DeleteMapping("delete")
    public Result<Boolean> deleteFileManager(@RequestBody IdRequest request) {
        return Result.success(fileManagerService.deleteFileManager(request.getId()));
    }
}
