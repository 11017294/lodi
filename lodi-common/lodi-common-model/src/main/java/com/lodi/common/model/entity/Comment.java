package com.lodi.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lodi.common.mybatis.domain.BaseEntity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 评论
 *
 * @author MaybeBin
 * @createDate 2024-04-03
 */
@Data
@Accessors(chain = true)
@TableName("t_comment")
public class Comment extends BaseEntity<Comment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 该条评论下的，一级评论ID
    */
    private Long firstCommentId;

    /**
    * 回复某条评论的id
    */
    private Long toId;

    /**
    * 文章id
    */
    private Long articleId;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 回复某个人的id
    */
    private Long toUserId;

    /**
    * 评论内容
    */
    private String content;

    /**
    * 评论来源: ARTICLE, MESSAGE_BOARD, ABOUT
    */
    private String source;
}
