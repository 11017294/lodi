package com.lodi.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lodi.common.core.web.domain.BaseEntity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Data
@Accessors(chain = true)
@TableName("t_article")
public class Article extends BaseEntity<Article> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 标题
    */
    private String title;

    /**
    * 文章简介
    */
    private String summary;

    /**
    * 内容
    */
    private String content;

    /**
    * 封面
    */
    private String cover;

    /**
    * 作者ID
    */
    private Long userId;

    /**
    * 标签ID
    */
    private String tagsId;

    /**
    * 文章类别ID
    */
    private Long categoryId;

    /**
    * 是否发布：0-否 1-是
    */
    private Integer isPublish;

    /**
    * 是否开启评论：0-否 1-是
    */
    private Integer openComment;

    /**
    * 文章点击数
    */
    private Integer clickCount;

    /**
    * 收藏次数
    */
    private Integer collectCount;

    /**
    * 点赞次数
    */
    private Integer upvoteCount;

    /**
    * vip文章：0-普通文章 1-vip文章
    */
    private Integer vipArticle;

    /**
    * 审核状态：0-未审核 1-审核通过 2-审核不通过
    */
    private Integer auditStatus;

    /**
    * 排序字段
    */
    private Integer sort;
}
