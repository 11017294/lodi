package com.lodi.common.model.request.fileManager;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文件管理更新 请求体
 *
 * @author MaybeBin
 * @createDate 2024-06-27
 */
@Data
@Schema(description = "文件管理更新 请求体")
public class FileManagerUpdateRequest implements Serializable {

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

}
