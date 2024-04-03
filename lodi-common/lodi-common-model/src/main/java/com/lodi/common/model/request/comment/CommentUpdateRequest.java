package com.lodi.common.model.request.comment;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 评论更新 请求体
 *
 * @author MaybeBin
 * @createDate 2024-04-03
 */
@Data
@Schema(description = "评论更新 请求体")
public class CommentUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    @Positive
    private Long id;

    @Schema(description = "该条评论下的一级评论ID")
    private Long firstCommentId;

    @Schema(description = "回复某条评论的id")
    private Long toId;

    @Schema(description = "文章id")
    private Long articleId;

    @Schema(description = "用户uid")
    private Long userId;

    @Schema(description = "回复某个人的id")
    private Long toUserId;

    @Schema(description = "评论内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "评论内容不能为空")
    private String content;

    @Schema(description = "评论来源: ARTICLE, MESSAGE_BOARD, ABOUT", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "评论来源不能为空")
    private String source;
}
