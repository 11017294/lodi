package com.lodi.common.model.request.category;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 添加文章类别 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Data
@Schema(title = "添加文章类别 请求体")
public class CategoryAddRequest implements Serializable {

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
