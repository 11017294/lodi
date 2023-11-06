package com.lodi.common.model.request.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章审核 请求体
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Data
@Schema(title = "文章审核 请求体")
public class AuditArticleRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "id", description = "id", example = "10000")
    private Long id;

    /**
     * 0-未审核 1-审核通过 2-审核不通过
     */
    @Schema(title = "审核状态", description = "审核状态", example = "1")
    private Integer auditStatus;

}
