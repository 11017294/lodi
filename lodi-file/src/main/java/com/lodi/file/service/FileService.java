package com.lodi.file.service;

/**
 * 文件上传 服务层
 *
 * @author MaybeBin
 * @createDate 2023-11-20
 */
public interface FileService {

    /**
     * 指定目录上传文件
     *
     * @param file          文件信息
     * @param fileDirectory 文件目录
     * @return
     */
    String upload(byte[] file, String fileDirectory);

    /**
     * 删除文件
     *
     * @param fileName      文件名称
     * @param fileDirectory 文件目录
     * @return
     */
    int delete(String fileName, String fileDirectory);

}
