package com.lodi.xo.service.impl;

import com.lodi.api.RemoteFileService;
import com.lodi.common.core.constant.FileConstant;
import com.lodi.common.core.constant.FileDirectoryConstant;
import com.lodi.common.core.domain.Result;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.entity.FileManager;
import com.lodi.common.satoken.utils.LoginHelper;
import com.lodi.xo.service.FileManagerService;
import com.lodi.xo.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;

import static com.lodi.common.core.constant.FileConstant.FILE_BASE_PATH;

/**
 * 文件服务
 *
 * @author MaybeBin
 * @createDate 2024-06-27
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileManagerService fileManagerService;
    @Resource
    private RemoteFileService remoteFileService;

    @Override
    public String uploadAvatar(MultipartFile avatarFile) {
        // 上传头像
        String path = remoteFileService.upload(avatarFile, FileDirectoryConstant.AVATAR).getData();
        if (StringUtils.isBlank(path)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        // 保存到数据库
        FileManager fileManager = new FileManager();
        fileManager.setFilePath(path)
                .setFileName(path.substring(FILE_BASE_PATH.length()))
                .setFileType(FileConstant.FILE)
                .setParentId(0L)
                .setCreatedBy(LoginHelper.getUserId())
                .setUpdatedBy(LoginHelper.getUserId())
                .setFileSize(avatarFile.getSize());
        fileManagerService.save(fileManager);
        return path;
    }

    @Override
    public String uploadMarkdownImage(MultipartFile imageFile) {
        // 上传图片
        String path = remoteFileService.upload(imageFile, FileDirectoryConstant.BLOG).getData();
        if (StringUtils.isBlank(path)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        // 保存到数据库
        FileManager fileManager = new FileManager();
        fileManager.setFilePath(path)
                .setFileName(path.substring(FILE_BASE_PATH.length()))
                .setFileType(FileConstant.FILE)
                .setParentId(0L)
                .setCreatedBy(LoginHelper.getUserId())
                .setUpdatedBy(LoginHelper.getUserId())
                .setFileSize(imageFile.getSize());
        fileManagerService.save(fileManager);
        return path;
    }

    @Override
    public void deleteFile(Long id) {
        FileManager fileManager = fileManagerService.getById(id);
        if (Objects.isNull(fileManager)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        // 检测是否当前用户或管理员
        LoginHelper.checkLoginUserOrAdmin(fileManager.getCreatedBy());
        String[] fileNames = fileManager.getFileName().split("/");
        Result<Integer> result = remoteFileService.delete(fileNames[0], "/" + fileNames[1]);
        if (result.getData() == -1) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除图片失败");
        }
        fileManagerService.removeById(id);
    }

    @Override
    public Boolean deleteImageByPath(String filePath) {
        FileManager fileManager = fileManagerService.queryByFilePath(filePath);
        if (Objects.isNull(fileManager)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        // 检测是否当前用户或管理员
        // todo 粗暴解决 待优化
        LoginHelper.checkLoginUserOrAdmin(fileManager.getCreatedBy());
        String[] fileNames = fileManager.getFileName().split("/");
        Result<Integer> result = remoteFileService.delete(fileNames[1], fileNames[0] + "/");
        if (result.getData() == -1) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除图片失败");
        }
        return fileManagerService.deleteFileManager(fileManager.getId());
    }

}