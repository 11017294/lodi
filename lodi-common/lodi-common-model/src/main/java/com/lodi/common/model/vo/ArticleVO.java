package com.lodi.common.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章 视图
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Data
@Schema(description = "文章 视图")
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "文章简介")
    private String summary;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "封面")
    private String cover;

    @Schema(description = "作者ID")
    private Long userId;

    @Schema(description = "作者昵称")
    private String nickname;

    @Schema(description = "作者账号")
    private String username;

    @Schema(description = "用户头像")
    private String userAvatar;

    @Schema(description = "文章有多个标签，用逗号隔开")
    private String tagsId;

    @Schema(description = "文章有多个标签")
    private List<String> tagsList;

    @Schema(description = "文章类别ID")
    private Long categoryId;

    @Schema(description = "文章类别")
    private String categoryName;

    @Schema(description = "是否发布：0-否 1-是")
    private Integer isPublish;

    @Schema(description = "是否开启评论：0-否 1-是")
    private Integer openComment;

    @Schema(description = "文章点击数")
    private Integer clickCount;

    @Schema(description = "收藏次数")
    private Integer collectCount;

    @Schema(description = "点赞次数")
    private Integer upvoteCount;

    @Schema(description = "评论数")
    private Long commentCount;

    @Schema(description = "vip文章：0-普通文章 1-vip文章")
    private Integer vipArticle;

    @Schema(description = "审核状态：0-未审核 1-审核通过 2-审核不通过")
    private Integer auditStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Schema(description = "排序字段")
    private Integer sort;
}
