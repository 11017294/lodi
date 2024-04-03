package com.lodi.common.model.request.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * 文章审核 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Data
@Schema(description = "文章审核 请求体")
public class AuditArticleRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id", example = "10000")
    @NotNull(message = "Id 不能为空")
    @Positive
    private Long id;

    /**
     * 0-未审核 1-审核通过 2-审核不通过
     */
    @Schema(description = "审核状态", example = "1")
    @NotNull(message = "审核状态不能为空")
    private Integer auditStatus;

}
