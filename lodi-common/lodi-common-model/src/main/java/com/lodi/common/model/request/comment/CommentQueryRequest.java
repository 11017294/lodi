package com.lodi.common.model.request.comment;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 评论查询
 *
 * @author MaybeBin
 * @createDate 2024-04-10
 */
@Data
@Schema(description = "评论查询请求体")
public class CommentQueryRequest extends PageRequest {

    @Schema(description = "文章id")
    private Long articleId;

    @Schema(description = "评论来源: ARTICLE, MESSAGE_BOARD, ABOUT", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "评论来源不能为空")
    private String source;

}
