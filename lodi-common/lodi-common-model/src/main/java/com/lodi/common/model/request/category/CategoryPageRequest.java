package com.lodi.common.model.request.category;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文章类别分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Data
@Schema(description = "文章类别分页查询 请求体")
public class CategoryPageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @Schema(description = "类别名称")
    private String name;

    @Schema(description = "类别简介")
    private String content;

    @Schema(description = "排序字段，越大越靠前")
    private Integer sort;

    @Schema(description = "点击数")
    private Integer clickCount;
}
