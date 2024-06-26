package com.lodi.common.model.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 用户更新 请求体
 *
 * @author MaybeBin
 * @createDate 2023-10-26
 */
@Data
@Schema(description = "用户更新 请求体")
public class UserUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "昵称", example = "lodi")
    private String nickname;

    @Schema(description = "用户头像")
    private String userAvatar;

    @Schema(description = "自我简介", maxLength = 150)
    private String summary;

    @Schema(description = "出生年月日", format = "yyyy-MM-dd")
    private LocalDate birthday;

    @Schema(description = "性别（0-默认 1-男 2-女）")
    private Integer gender;

    @Schema(description = "邮箱", format = "email")
    private String email;

    @Schema(description = "用户角色")
    private String userRole;

    @Schema(description = "是否开启邮件通知（0-关闭 1-开启）")
    private Integer startEmailNotify;

    @Schema(description = "状态（0-禁用 1-正常）")
    private Integer status;

    @Schema(description = "评论状态（0-禁言 1-正常）")
    private Integer commentStatus;
}
