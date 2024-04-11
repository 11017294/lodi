package com.lodi.common.model.request.navigate;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 导航分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Data
@Schema(description = "导航分页查询 请求体")
public class NavigatePageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "简介")
    private String summary;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "url")
    private String url;

    @Schema(description = "网站图标路径")
    private String faviconUrl;

    @Schema(description = "导航类型id")
    private Long navCategoryId;

    @Schema(description = "排序字段")
    private Integer sort;
}
