package com.lodi.common.model.request.category;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 文章类别更新 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Data
@Schema(description = "文章类别更新 请求体")
public class CategoryUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Id 不能为空")
    @Positive
    private Long id;

    @Schema(description = "类别名称")
    private String name;

    @Schema(description = "类别简介")
    private String content;

    @Schema(description = "排序字段，越大越靠前")
    private Integer sort;

    @Schema(description = "点击数")
    private Integer clickCount;
}
