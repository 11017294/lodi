package com.lodi.common.model.vo;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文章类别 视图
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Data
@Schema(description = "文章类别 视图")
public class CategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
