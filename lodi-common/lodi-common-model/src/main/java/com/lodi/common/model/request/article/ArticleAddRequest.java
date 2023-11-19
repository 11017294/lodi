package com.lodi.common.model.request.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加文章 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-02
 */
@Data
@Schema(title = "添加文章 请求体")
public class ArticleAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "标题", description = "标题")
    private String title;

    @Schema(title = "文章简介", description = "文章简介")
    private String summary;

    @Schema(title = "内容", description = "内容")
    private String content;

    @Schema(title = "封面", description = "封面")
    private String cover;

    @Schema(title = "标签ID", description = "标签ID", example = "100,101,102")
    private String tagId;

    @Schema(title = "文章类别ID", description = "文章类别ID", example = "100")
    private Long categoriesId;

    /**
     * 0-否 1-是
     */
    @Schema(title = "是否发布", description = "是否发布", example = "0")
    private Integer isPublish;

    /**
     * 0-否 1-是
     */
    @Schema(title = "是否开启评论", description = "是否开启评论", example = "0")
    private Integer openComment;

    @Schema(title = "排序字段", description = "排序字段")
    private Integer sort;

}
