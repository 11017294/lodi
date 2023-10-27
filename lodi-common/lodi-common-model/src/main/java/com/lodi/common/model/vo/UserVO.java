package com.lodi.common.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户 视图
 * @author MaybeBin
 * @createDate 2023-10-26
 */
@Data
@Schema(title = "用户视图")
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户账号
     */
    @Schema(title = "用户账号")
    private String userAccount;

    /**
     * 用户名
     */
    @Schema(title = "用户名")
    private String userName;

    /**
     * 用户头像
     */
    @Schema(title = "用户头像")
    private String userAvatar;

    /**
     * 自我简介最多150字
     */
    @Schema(title = "自我简介最多", maxLength = 150)
    private String summary;

    /**
     * 出生年月日
     */
    @Schema(title = "出生年月日")
    private Date birthday;

    /**
     * 性别：0-默认 1-男 2-女
     */
    @Schema(title = "性别")
    private Integer gender;

    /**
     * 邮箱
     */
    @Schema(title = "邮箱")
    private String email;

    /**
     * 用户角色：user / admin
     */
    @Schema(title = "用户角色")
    private String userRole;

    /**
     * 登录次数
     */
    @Schema(title = "登录次数")
    private Integer loginCount;

    /**
     * ip来源
     */
    @Schema(title = "ip来源")
    private String ipSource;

    /**
     * 浏览器
     */
    @Schema(title = "浏览器")
    private String browser;

    /**
     * 操作系统
     */
    @Schema(title = "操作系统")
    private String os;

    /**
     * 最后登录时间
     */
    @Schema(title = "最后登录时间")
    private Date lastLoginTime;

    /**
     * 最后登录IP
     */
    @Schema(title = "最后登录IP")
    private String lastLoginIp;

    /**
     * 是否开启邮件通知：0-关闭 1-开启
     */
    @Schema(title = "是否开启邮件通知")
    private Integer startEmailNotify;

    /**
     * 状态：0-禁用 1-正常
     */
    @Schema(title = "状态")
    private Integer status;

    /**
     * 评论状态：0-禁言 1-正常
     */
    @Schema(title = "评论状态")
    private Integer commentStatus;

}
