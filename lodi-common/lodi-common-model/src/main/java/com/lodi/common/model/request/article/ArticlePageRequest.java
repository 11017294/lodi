package com.lodi.common.model.request.article;

import com.lodi.common.core.web.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文章分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Data
@Schema(title = "文章分页查询 请求体")
public class ArticlePageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @Schema(title = "标题", description = "标题")
    private String title;

    @Schema(title = "文章简介", description = "文章简介")
    private String summary;

    @Schema(title = "内容", description = "内容")
    private String content;

    @Schema(title = "作者ID", description = "作者ID")
    private Long userId;

    @Schema(title = "标签ID", description = "标签ID")
    private String tagId;

    @Schema(title = "文章类别ID", description = "文章类别ID")
    private Long categoryId;

    @Schema(title = "是否发布：0-否 1-是", description = "是否发布：0-否 1-是")
    private Integer isPublish;

    @Schema(title = "是否开启评论：0-否 1-是", description = "是否开启评论：0-否 1-是")
    private Integer openComment;

    @Schema(title = "vip文章：0-普通文章 1-vip文章", description = "vip文章：0-普通文章 1-vip文章")
    private Integer vipArticle;

    @Schema(title = "审核状态：0-未审核 1-审核通过 2-审核不通过", description = "审核状态：0-未审核 1-审核通过 2-审核不通过")
    private Integer auditStatus;

    @Schema(title = "排序字段", description = "排序字段")
    private Integer sort;
}
