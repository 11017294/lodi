
-- 创建库
create database if not exists lodi;

-- 切换库
use lodi;

-- 用户表
CREATE TABLE IF NOT EXISTS lodi.t_user
(
    id                 BIGINT         NOT NULL AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    user_account       VARCHAR(256)   NOT NULL                COMMENT '用户账号',
    user_name          VARCHAR(256)   NOT NULL                COMMENT '用户名',
    user_password      VARCHAR(256)   NOT NULL                COMMENT '用户密码',
    user_avatar        VARCHAR(256)   NOT NULL                COMMENT '用户头像',
    summary            VARCHAR(200)                           COMMENT '自我简介最多150字',
    birthday           DATE                                   COMMENT '出生年月日',
    gender             TINYINT        NOT NULL DEFAULT 0      COMMENT '性别：0-默认 1-男 2-女',
    email              VARCHAR(64)                            COMMENT '邮箱',
    user_role          VARCHAR(64)    NOT NULL DEFAULT 'user' COMMENT '用户角色：user / admin',
    login_count        INT(10)        NOT NULL DEFAULT 0      COMMENT '登录次数',
    ip_source          VARCHAR(255)                           COMMENT 'ip来源',
    browser            VARCHAR(255)                           COMMENT '浏览器',
    os                 VARCHAR(255)                           COMMENT '操作系统',
    last_login_time    DATETIME                               COMMENT '最后登录时间',
    last_login_ip      VARCHAR(50)    NOT NULL DEFAULT '127.0.0.1' COMMENT '最后登录IP',
    start_email_notify TINYINT        NOT NULL DEFAULT 0      COMMENT '是否开启邮件通知：0-关闭 1-开启 ',
    status             TINYINT        NOT NULL DEFAULT 1      COMMENT '状态：0-禁用 1-正常',
    comment_status     TINYINT        NOT NULL DEFAULT 1      COMMENT '评论状态：0-禁言 1-正常',
    create_time        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete          TINYINT        NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删 1-已删'
)  comment '用户表';

-- 博客表
CREATE TABLE IF NOT EXISTS lodi.t_blog
(
    id                 BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title              VARCHAR(128)   NOT NULL              COMMENT '标题',
    summary            VARCHAR(256)                         COMMENT '文章简介',
    content            LONGTEXT       NOT NULL              COMMENT '内容',
    cover              VARCHAR(256)   NOT NULL              COMMENT '封面',
    user_id            BIGINT         NOT NULL              COMMENT '作者uid',
    tag_uid            VARCHAR(255)                         COMMENT '标签id',
    blog_sort_id       BIGINT                               COMMENT '博客分类UID',
    is_publish         TINYINT        NOT NULL DEFAULT 1    COMMENT '是否发布：0-否 1-是',
    open_comment       TINYINT        NOT NULL DEFAULT 1    COMMENT '是否开启评论：0-否 1-是',
    click_count        INT(10)        NOT NULL DEFAULT 0    COMMENT '博客点击数',
    collect_count      INT(10)        NOT NULL DEFAULT 0    COMMENT '收藏次数',
    upvote_count       INT(10)        NOT NULL DEFAULT 0    COMMENT '点赞次数',

    vip_article        TINYINT        NOT NULL DEFAULT 0    COMMENT 'vip文章：0-普通文章 1-vip文章',
    audit_status       TINYINT        NOT NULL DEFAULT 0    COMMENT '审核状态：0-未审核 1-审核通过 2-审核不通过',
    sort               int(10)        NOT NULL DEFAULT 0    COMMENT '排序字段',

    create_time        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete          TINYINT        NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删 1-已删'
) COMMENT '博客表';

-- 博客分类表
CREATE TABLE IF NOT EXISTS lodi.t_blog_sort
(
    id                 BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sort_name          VARCHAR(255)                                    COMMENT '分类名',
    content            VARCHAR(255)                                    COMMENT '分类简介',
    sort               INT(10)        NOT NULL DEFAULT 0               COMMENT '排序字段，越大越靠前',
    click_count        INT(10)        NOT NULL DEFAULT 0               COMMENT '点击数',
    create_time        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete          TINYINT        NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删 1-已删'
) COMMENT '博客分类表';

-- 博客标签表
CREATE TABLE IF NOT EXISTS lodi.t_tag
(
    id                 BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tag_name           VARCHAR(255)                                    COMMENT '标签名',
    content            VARCHAR(255)                                    COMMENT '标签简介',
    sort               INT(10)        NOT NULL DEFAULT 0               COMMENT '排序字段，越大越靠前',
    click_count        INT(10)        NOT NULL DEFAULT 0               COMMENT '点击数',
    create_time        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete          TINYINT        NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删 1-已删'
) COMMENT '标签表';
