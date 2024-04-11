package com.lodi.common.model.vo;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 导航 视图
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Data
@Schema(description = "导航 视图")
public class NavigateVO implements Serializable {

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

    @Schema(description = "点击数")
    private Integer clickCount;

    @Schema(description = "排序字段")
    private Integer sort;
}
