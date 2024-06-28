package com.lodi.xo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.FileManager;
import com.lodi.common.model.request.fileManager.FileManagerAddRequest;
import com.lodi.common.model.request.fileManager.FileManagerPageRequest;
import com.lodi.common.model.request.fileManager.FileManagerUpdateRequest;
import com.lodi.common.mybatis.service.BaseService;

/**
 * 文件管理 服务层
 *
 * @author MaybeBin
 * @createDate 2024-06-27
 */
public interface FileManagerService extends BaseService<FileManager> {

    /**
     * 新增文件管理
     *
     * @param addRequest 新增请求体
     * @return
     */
    Boolean insertFileManager(FileManagerAddRequest addRequest);

    /**
     * 更新文件管理
     *
     * @param updateRequest 更新请求体
     * @return
     */
    Boolean updateFileManager(FileManagerUpdateRequest updateRequest);

    /**
     * 删除文件管理
     *
     * @param id 文件管理ID
     * @return
     */
    Boolean deleteFileManager(Long id);

    /**
     * 获取文件管理
     *
     * @param pageRequest 分页查询请求体
     * @return
     */
    Page<FileManager> getFileManagerPage(FileManagerPageRequest pageRequest);

    /**
     * 根据文件路径查询文件管理
     *
     * @param filePath 文件路径
     * @return
     */
    FileManager queryByFilePath(String filePath);
}
