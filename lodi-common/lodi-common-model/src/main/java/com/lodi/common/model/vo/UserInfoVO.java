package com.lodi.common.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 用户信息 视图
 *
 * @author MaybeBin
 * @createDate 2023-10-26
 */
@Data
@Schema(description = "用户信息视图")
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "用户头像")
    private String userAvatar;

    @Schema(description = "自我简介最多", maxLength = 150)
    private String summary;

    @Schema(description = "出生年月日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Schema(description = "性别（0-默认 1-男 2-女）")
    private Integer gender;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "是否开启邮件通知（0-关闭 1-开启）")
    private Integer startEmailNotify;

    @Schema(description = "注册日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime;

}
