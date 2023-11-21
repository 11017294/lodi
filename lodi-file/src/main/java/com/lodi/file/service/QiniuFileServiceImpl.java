package com.lodi.file.service;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.file.utils.QiniuUtil;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 七牛云文件 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-11-21
 */
@Slf4j
@Service
public class QiniuFileServiceImpl implements FileService {

    @Resource
    private QiniuUtil qiniuUtil;

    @Override
    public String upload(byte[] file, String fileDirectory) {
        if (file == null || file.length == 0) {
            throw new BusinessException(ErrorCode.FILE_IS_EMPTY);
        }
        try {
            return qiniuUtil.uploadQiniu(file, fileDirectory);
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
