package com.lodi.common.model.request.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 添加文章 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-02
 */
@Data
@Schema(description = "添加文章 请求体")
public class ArticleAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @Schema(description = "文章简介")
    @NotBlank(message = "文章简介不能为空")
    private String summary;

    @Schema(description = "内容")
    @NotBlank(message = "内容不能为空")
    private String content;

    @Schema(description = "封面")
    private String cover;

    @Schema(description = "标签ID")
    @Size(min = 1, message = "标签数量必须大于等于1")
    private Long[] tags;

    @Schema(description = "文章类别ID")
    @NotNull(message = "文章类别ID不能为空")
    @Positive
    private Long categoryId;

    @Schema(description = "是否发布：0-否 1-是")
    @NotNull(message = "是否发布不能为空")
    private Integer isPublish;

    @Schema(description = "是否开启评论：0-否 1-是")
    private Integer openComment;

    @Schema(description = "排序字段")
    private Integer sort;
}
