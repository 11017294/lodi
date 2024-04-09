package com.lodi.common.model.request.comment;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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

    @Schema(description = "评论内容", maxLength = 2048, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "评论内容不能为空")
    @Length(max = 2048, message = "评论内容不能超过2048个字符")
    private String content;

}
