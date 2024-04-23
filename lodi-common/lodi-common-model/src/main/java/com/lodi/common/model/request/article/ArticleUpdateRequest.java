package com.lodi.common.model.request.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

/**
 * 文章更新 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Data
@Schema(description = "文章更新 请求体")
public class ArticleUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @NotNull(message = "Id 不能为空")
    @Positive
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "文章简介")
    private String summary;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "封面")
    private String cover;

    @Schema(description = "标签ID")
    private List<Long> tags;

    @Schema(description = "文章类别ID")
    private Long categoryId;

    @Schema(description = "是否发布：0-否 1-是")
    private Integer isPublish;

    @Schema(description = "是否开启评论：0-否 1-是")
    private Integer openComment;

    @Schema(description = "排序字段")
    private Integer sort;
}
