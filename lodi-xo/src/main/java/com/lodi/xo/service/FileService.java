package com.lodi.xo.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 *
 * @author MaybeBin
 * @createDate 2024-06-27
 */
public interface FileService {

    /**
     * 上传头像
     *
     * @param avatarFile
     * @return
     */
    String uploadAvatar(MultipartFile avatarFile);

    /**
     * markdown图片上传
     *
     * @param imageFile
     * @return
     */
    String uploadMarkdownImage(MultipartFile imageFile);

    /**
     * 根据id删除文件
     *
     * @param id 文件管理ID
     */
    void deleteFile(Long id);

    /**
     * 根据文件路径删除文件
     *
     * @param filePath 文件路径
     */
    Boolean deleteImageByPath(String filePath);
}
