package com.lodi.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lodi.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 文章
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Data
@TableName(value = "t_article")
public class Article extends BaseEntity<Article> {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 文章简介
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 作者id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 标签ID
     */
    @TableField(value = "tag_id")
    private String tagId;

    /**
     * 文章类别ID
     */
    @TableField(value = "categories_id")
    private Long categoriesId;

    /**
     * 是否发布：0-否 1-是
     */
    @TableField(value = "is_publish")
    private Integer isPublish;

    /**
     * 是否开启评论：0-否 1-是
     */
    @TableField(value = "open_comment")
    private Integer openComment;

    /**
     * 文章点击数
     */
    @TableField(value = "click_count")
    private Integer clickCount;

    /**
     * 收藏次数
     */
    @TableField(value = "collect_count")
    private Integer collectCount;

    /**
     * 点赞次数
     */
    @TableField(value = "upvote_count")
    private Integer upvoteCount;

    /**
     * vip文章：0-普通文章 1-vip文章
     */
    @TableField(value = "vip_article")
    private Integer vipArticle;

    /**
     * 审核状态：0-未审核 1-审核通过 2-审核不通过
     */
    @TableField(value = "audit_status")
    private Integer auditStatus;

    /**
     * 排序字段
     */
    @TableField(value = "sort")
    private Integer sort;

}
