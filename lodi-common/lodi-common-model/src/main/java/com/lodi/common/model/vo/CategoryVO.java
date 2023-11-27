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
@Schema(title = "文章类别 视图")
public class CategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(title = "类别名称", description = "类别名称")
    private String name;

    @Schema(title = "类别简介", description = "类别简介")
    private String content;

    @Schema(title = "排序字段，越大越靠前", description = "排序字段，越大越靠前")
    private Integer sort;

    @Schema(title = "点击数", description = "点击数")
    private Integer clickCount;
}
