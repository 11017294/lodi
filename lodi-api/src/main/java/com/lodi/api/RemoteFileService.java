package com.lodi.api;

import com.lodi.common.core.constant.ServiceNameConstants;
import com.lodi.common.core.web.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务 远程调用
 *
 * @author MaybeBin
 * @createDate 2023-11-20
 */
@FeignClient(ServiceNameConstants.FILE_SERVICE)
public interface RemoteFileService {

    /**
     * 上传文件
     *
     * @param file          文件信息
     * @param fileDirectory 文件目录
     * @return
     */
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<String> upload(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "fileDirectory") String fileDirectory);

    /**
     * 删除文件
     *
     * @param fileName
     * @param fileDirectory
     * @return
     */
    @PostMapping("delete")
    Result<String> delete(@RequestParam(value = "fileName") String fileName, @RequestParam(value = "fileDirectory") String fileDirectory);

}
