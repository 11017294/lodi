package com.lodi.common.model.convert.fileManager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.FileManager;
import com.lodi.common.model.vo.FileManagerVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.lodi.common.model.request.fileManager.FileManagerAddRequest;
import com.lodi.common.model.request.fileManager.FileManagerUpdateRequest;

import java.util.List;

/**
 * 文件管理 Convert
 *
 * @author MaybeBin
 * @createDate 2024-06-27
 */
@Mapper
public interface FileManagerConvert {

    FileManagerConvert INSTANCE = Mappers.getMapper(FileManagerConvert.class);

    FileManager toEntity(FileManagerAddRequest addRequest);

    FileManager toEntity(FileManagerUpdateRequest updateRequest);

    FileManagerVO toVO(FileManager fileManager);
    
    List<FileManagerVO> toVO(List<FileManager> fileManagerList); 
    
    Page<FileManagerVO> toVO(Page<FileManager> fileManagerPage);
}