package com.lodi.common.model.request.article;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 文章分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Data
@Schema(description = "文章分页查询 请求体")
public class ArticlePageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "文章简介")
    private String summary;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "作者ID")
    private Long userId;

    @Schema(description = "标签ID")
    private String tagsId;

    @Schema(description = "文章类别ID")
    private Long categoryId;

    @Schema(description = "是否发布：0-否 1-是")
    private Integer isPublish;

    @Schema(description = "是否开启评论：0-否 1-是")
    private Integer openComment;

    @Schema(description = "vip文章：0-普通文章 1-vip文章")
    private Integer vipArticle;

    @Schema(description = "审核状态：0-未审核 1-审核通过 2-审核不通过")
    private Integer auditStatus;

    @Schema(description = "排序字段")
    private Integer sort;
}
