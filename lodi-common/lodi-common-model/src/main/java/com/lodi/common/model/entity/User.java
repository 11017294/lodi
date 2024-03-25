package com.lodi.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lodi.common.mybatis.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author MaybeBin
 * @createDate 2023-09-20
 */
@Data
@TableName(value = "t_user")
public class User extends BaseEntity<User> {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 自我简介最多150字
     */
    private String summary;

    /**
     * 出生年月日
     */
    private Date birthday;

    /**
     * 性别：0-默认 1-男 2-女
     */
    private Integer gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户角色：user / admin
     */
    private String userRole;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * ip来源
     */
    private String ipSource;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

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

    private static final long serialVersionUID = 1L;

}
