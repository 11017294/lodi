package com.lodi.common.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 评论树形视图
 *
 * @author MaybeBin
 * @createDate 2024-04-08
 */
@Data
@Schema(description = "评论树形视图")
public class CommentTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "该条评论下的，一级评论ID")
    private Long firstCommentId;

    @Schema(description = "被评论的id")
    private Long toId;

    @Schema(description = "评论用户id")
    private Long userId;

    @Schema(description = "评论用户昵称")
    private String username;

    @Schema(description = "评论用户头像")
    private String userAvatar;

    @Schema(description = "被评论用户的id")
    private Long toUserId;

    @Schema(description = "被评论用户的昵称")
    private String toUsername;

    @Schema(description = "被评论用户的头像")
    private String toUserAvatar;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "子评论")
    private List<CommentTreeVO> children;
}
