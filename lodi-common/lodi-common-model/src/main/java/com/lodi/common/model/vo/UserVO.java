package com.lodi.common.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户 视图
 *
 * @author MaybeBin
 * @createDate 2023-10-26
 */
@Data
@Schema(description = "用户视图")
public class UserVO implements Serializable {

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
    private Date birthday;

    @Schema(description = "性别（0-默认 1-男 2-女）")
    private Integer gender;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户角色（user / admin）")
    private String userRole;

    @Schema(description = "登录次数")
    private Integer loginCount;

    @Schema(description = "ip来源")
    private String ipSource;

    @Schema(description = "浏览器")
    private String browser;

    @Schema(description = "操作系统")
    private String os;

    @Schema(description = "最后登录时间")
    private Date lastLoginTime;

    @Schema(description = "最后登录IP")
    private String lastLoginIp;

    @Schema(description = "是否开启邮件通知（0-关闭 1-开启）")
    private Integer startEmailNotify;

    @Schema(description = "状态（0-禁用 1-正常）")
    private Integer status;

    @Schema(description = "评论状态（0-禁言 1-正常）")
    private Integer commentStatus;

    @Schema(description = "注册日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

}
