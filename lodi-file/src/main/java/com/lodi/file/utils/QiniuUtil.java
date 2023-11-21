package com.lodi.file.utils;

import com.google.gson.Gson;
import com.lodi.common.core.utils.StrUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 七牛云 文件管理工具
 *
 * @author: MaybeBin
 * @createTime: 2023-11-20
 */
@Slf4j
@Data
@Component
@ConfigurationProperties("lodi.qiniu-yun")
public class QiniuUtil {

    private String accessKey;
    private String secretKey;
    private String bucket;

    /**
     * 上传文件
     *
     * @param filePath
     * @param catalog
     * @return
     * @throws QiniuException
     */
    public String uploadQiniu(String filePath, String catalog) throws QiniuException {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = catalog + StrUtils.getUUID();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Response response = uploadManager.put(filePath, key, upToken);
        // 解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("{七牛图片上传key: " + putRet.key + ",七牛图片上传hash: " + putRet.hash + "}");
        return putRet.key;
    }

    /**
     * 上传文件
     *
     * @param bytes
     * @param catalog
     * @return
     * @throws QiniuException
     */
    public String uploadQiniu(byte[] bytes, String catalog) throws QiniuException {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = catalog + StrUtils.getUUID();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Response response = uploadManager.put(bytes, key, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("{七牛图片上传key: " + putRet.key + ",七牛图片上传hash: " + putRet.hash + "}");
        return putRet.key;
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    public int deleteFileFromQiniu(String fileName) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huadong());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            Response delete = bucketManager.delete(bucket, fileName);
            log.info("{七牛删除图片statusCode: " + delete.statusCode + "}");
            return delete.statusCode;
        } catch (QiniuException ex) {
            log.error("code:{}, message:{}", ex.code(), ex.response.toString());
        }
        return -1;
    }

}
