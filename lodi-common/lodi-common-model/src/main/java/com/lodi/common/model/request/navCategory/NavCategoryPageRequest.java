package com.lodi.common.model.request.navCategory;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 导航分类表分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Data
@Schema(description = "导航分类表分页查询 请求体")
public class NavCategoryPageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分类名")
    private String name;

    @Schema(description = "分类简介")
    private String content;

    @Schema(description = "排序字段")
    private Integer sort;
}
