package com.lodi.file.service;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.file.utils.QiniuUtil;
import com.qiniu.common.QiniuException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.lodi.common.core.constant.FileConstant.FILE_BASE_PATH;

/**
 * 七牛云文件 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-11-21
 */
@Slf4j
@Service
@AllArgsConstructor
public class QiniuFileServiceImpl implements FileService {

    private final QiniuUtil qiniuUtil;

    @Override
    public String upload(byte[] file, String fileDirectory) {
        if (file == null || file.length == 0) {
            throw new BusinessException(ErrorCode.FILE_IS_EMPTY);
        }
        try {
            return FILE_BASE_PATH + qiniuUtil.uploadQiniu(file, fileDirectory);
        } catch (QiniuException e) {
            log.error("上传文件到七牛云失败", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件服务异常");
        }
    }

    @Override
    public int delete(String fileName, String fileDirectory) {
        return qiniuUtil.deleteFileFromQiniu(fileDirectory + fileName);
    }
}
