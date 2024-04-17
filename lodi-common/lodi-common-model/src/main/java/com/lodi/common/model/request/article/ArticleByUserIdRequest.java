package com.lodi.common.model.request.article;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 按userId查询 请求体
 *
 * @author MaybeBin
 * @since 2024-04-17
 */
@Data
@Schema(description = "按userId查询 请求体")
public class ArticleByUserIdRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Id 不能为空")
    @Positive
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

}
