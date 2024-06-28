package com.lodi.xo.service.impl;

import com.lodi.common.model.entity.FileManager;
import com.lodi.xo.mapper.FileManagerMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lodi.common.model.request.fileManager.FileManagerAddRequest;
import com.lodi.common.model.request.fileManager.FileManagerUpdateRequest;
import com.lodi.common.model.request.fileManager.FileManagerPageRequest;
import com.lodi.common.model.convert.fileManager.FileManagerConvert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.xo.service.FileManagerService;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文件管理 服务层实现
 *
 * @author MaybeBin
 * @createDate 2024-06-27
 */
@Service
public class FileManagerServiceImpl extends BaseServiceImpl<FileManagerMapper, FileManager> implements FileManagerService {

    @Override
    public Boolean insertFileManager(FileManagerAddRequest addRequest) {
        FileManager fileManager = FileManagerConvert.INSTANCE.toEntity(addRequest);
        return save(fileManager);
    }

    @Override
    public Boolean updateFileManager(FileManagerUpdateRequest updateRequest) {
        FileManager fileManager = FileManagerConvert.INSTANCE.toEntity(updateRequest);
        return updateById(fileManager);
    }

    @Override
    public Boolean deleteFileManager(Long id) {
        return removeById(id);
    }

    @Override
    public Page<FileManager> getFileManagerPage(FileManagerPageRequest pageRequest) {
        Page<FileManager> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<FileManager> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public FileManager queryByFilePath(String filePath) {
        LambdaQueryWrapper<FileManager> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileManager::getFilePath, filePath);
        return baseMapper.selectOne(queryWrapper);
    }

    private LambdaQueryWrapper<FileManager> buildQueryWrapper(FileManagerPageRequest pageRequest) {
        return new LambdaQueryWrapper<FileManager>();
    }
}
