package com.lodi.common.model.request.user;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 用户分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2023-10-26
 */
@Data
@Schema(description = "用户分页查询")
public class UserPageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名", example = "lodi")
    private String username;

    @Schema(description = "昵称", example = "lodi")
    private String nickname;

    @Schema(description = "用户头像", example = "")
    private String userAvatar;

    @Schema(description = "自我简介", example = "这个家伙很懒，什么都没留下", maxLength = 150)
    private String summary;

    @Schema(description = "出生年月日", format = "yyyy-MM-dd", example = "1999-01-01")
    private LocalDate birthday;

    /**
     * 性别：0-默认 1-男 2-女
     */
    private Integer gender;

    @Schema(description = "出生年月日", format = "email", example = "lodi@lodi.com")
    private String email;

    @Schema(description = "用户角色", example = "admin")
    private String userRole;

    /**
     * 是否开启邮件通知：0-关闭 1-开启
     */
    private Integer startEmailNotify;

    /**
     * 状态：0-禁用 1-正常
     */
    private Integer status;

    /**
     * 评论状态：0-禁言 1-正常
     */
    private Integer commentStatus;

}
