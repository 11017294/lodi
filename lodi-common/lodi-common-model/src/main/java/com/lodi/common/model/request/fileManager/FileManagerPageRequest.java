package com.lodi.common.model.request.fileManager;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文件管理分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2024-06-27
 */
@Data
@Schema(description = "文件管理分页查询 请求体")
public class FileManagerPageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "父ID目录关联")
    private Long parentId;

    @Schema(description = "文件名称")
    private String fileName;

    @Schema(description = "文件地址")
    private String filePath;

    @Schema(description = "文件类型：0-目录 1-文件")
    private Integer fileType;

    @Schema(description = "文件大小")
    private Long fileSize;

    @Schema(description = "文件内容哈希")
    private String fileHash;
}
