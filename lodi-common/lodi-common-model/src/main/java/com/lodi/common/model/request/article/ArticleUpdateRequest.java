package com.lodi.common.model.request.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * 文章更新 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Data
@Schema(title = "文章更新 请求体")
public class ArticleUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "id", description = "id")
    @NotNull(message = "Id 不能为空")
    @Positive
    private Long id;

    @Schema(title = "标题", description = "标题")
    private String title;

    @Schema(title = "文章简介", description = "文章简介")
    private String summary;

    @Schema(title = "内容", description = "内容")
    private String content;

    @Schema(title = "封面", description = "封面")
    private String cover;

    @Schema(title = "标签ID", description = "标签ID")
    private Long[] tags;

    @Schema(title = "文章类别ID", description = "文章类别ID")
    private Long categoryId;

    @Schema(title = "是否发布：0-否 1-是", description = "是否发布：0-否 1-是")
    private Integer isPublish;

    @Schema(title = "是否开启评论：0-否 1-是", description = "是否开启评论：0-否 1-是")
    private Integer openComment;

    @Schema(title = "排序字段", description = "排序字段")
    private Integer sort;
}
