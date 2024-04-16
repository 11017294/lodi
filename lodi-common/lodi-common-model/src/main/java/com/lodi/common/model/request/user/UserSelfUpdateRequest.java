package com.lodi.common.model.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户修改个人信息 请求体
 *
 * @author MaybeBin
 * @createDate 2024-04-16
 */
@Data
@Schema(description = "修改个人信息 请求体")
public class UserSelfUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "用户头像")
    private String userAvatar;

    @Schema(description = "自我简介", maxLength = 150)
    private String summary;

    @Schema(description = "出生年月日", format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Schema(description = "性别（0-默认 1-男 2-女）")
    private Integer gender;

    @Schema(description = "邮箱", format = "email")
    private String email;

    @Schema(description = "是否开启邮件通知（0-关闭 1-开启）")
    private Integer startEmailNotify;

}
