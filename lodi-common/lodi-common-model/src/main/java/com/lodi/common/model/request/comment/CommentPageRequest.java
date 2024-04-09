package com.lodi.common.model.request.comment;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 评论分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2024-04-03
 */
@Data
@Schema(description = "评论分页查询 请求体")
public class CommentPageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "该条评论下的，一级评论ID")
    private Long firstCommentId;

    @Schema(description = "回复某条评论的id")
    private Long toId;

    @Schema(description = "文章id")
    private Long articleId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "回复某个人的id")
    private Long toUserId;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "评论来源: ARTICLE, MESSAGE_BOARD, ABOUT")
    private String source;
}
