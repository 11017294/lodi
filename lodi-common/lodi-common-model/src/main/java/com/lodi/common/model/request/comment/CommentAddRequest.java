package com.lodi.common.model.request.comment;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 添加评论 请求体
 *
 * @author MaybeBin
 * @createDate 2024-04-03
 */
@Data
@Schema(description = "添加评论 请求体")
public class CommentAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "回复某条评论的id")
    private Long toId;

    @Schema(description = "文章id")
    private Long articleId;

    @Schema(description = "评论内容", maxLength = 2048, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "评论内容不能为空")
    @Length(max = 2048, message = "评论内容不能超过2048个字符")
    private String content;

    @Schema(description = "评论来源: ARTICLE, MESSAGE_BOARD, ABOUT", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "评论来源不能为空")
    private String source;
}
