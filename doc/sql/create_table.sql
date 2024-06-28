-- 创建库
create database if not exists lodi;

-- 切换库
use lodi;

-- 用户
CREATE TABLE IF NOT EXISTS lodi.t_user
(
    id                 BIGINT                                NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
    username           VARCHAR(256)                          NOT NULL COMMENT '用户名',
    nickname           VARCHAR(256)                          NOT NULL COMMENT '昵称',
    user_password      VARCHAR(256)                          NOT NULL COMMENT '用户密码',
    user_avatar        VARCHAR(256)                          NOT NULL COMMENT '用户头像',
    summary            VARCHAR(200)                          NULL COMMENT '自我简介最多150字',
    birthday           DATE                                  NULL COMMENT '出生年月日',
    gender             TINYINT                               NOT NULL DEFAULT 0 COMMENT '性别：0-默认 1-男 2-女',
    email              VARCHAR(64)                           NULL COMMENT '邮箱',
    user_role          VARCHAR(64)                           NOT NULL DEFAULT 'USER' COMMENT '用户角色：USER / ADMIN',
    login_count        INT(10)                               NOT NULL DEFAULT 0 COMMENT '登录次数',
    ip_source          VARCHAR(255)                          NULL COMMENT 'IP来源',
    browser            VARCHAR(255)                          NULL COMMENT '浏览器',
    os                 VARCHAR(255)                          NULL COMMENT '操作系统',
    last_login_time    DATETIME                              NULL COMMENT '最后登录时间',
    last_login_ip      VARCHAR(50) DEFAULT '127.0.0.1'       NOT NULL COMMENT '最后登录IP',
    start_email_notify TINYINT     DEFAULT 0                 NOT NULL COMMENT '是否开启邮件通知：0-关闭 1-开启 ',
    status             TINYINT     DEFAULT 1                 NOT NULL COMMENT '状态：0-禁用 1-正常',
    comment_status     TINYINT     DEFAULT 1                 NOT NULL COMMENT '评论状态：0-禁言 1-正常',
    create_time        DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time        DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete          TINYINT     DEFAULT 0                 NOT NULL COMMENT '是否删除：0-未删 1-已删'
) comment '用户';

-- 文章
CREATE TABLE IF NOT EXISTS lodi.t_article
(
    id            BIGINT                             NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
    title         VARCHAR(128)                       NOT NULL COMMENT '标题',
    summary       VARCHAR(256)                       NULL COMMENT '文章简介',
    content       LONGTEXT                           NOT NULL COMMENT '内容',
    cover         VARCHAR(256)                       NOT NULL COMMENT '封面',
    user_id       BIGINT                             NOT NULL COMMENT '作者ID',
    tags_id       VARCHAR(255)                       NULL COMMENT '标签ID',
    category_id   BIGINT                             NULL COMMENT '文章类别ID',
    is_publish    TINYINT  DEFAULT 1                 NOT NULL COMMENT '是否发布：0-否 1-是',
    open_comment  TINYINT  DEFAULT 1                 NOT NULL COMMENT '是否开启评论：0-否 1-是',
    click_count   INT(10)  DEFAULT 0                 NOT NULL COMMENT '文章点击数',
    collect_count INT(10)  DEFAULT 0                 NOT NULL COMMENT '收藏次数',
    upvote_count  INT(10)  DEFAULT 0                 NOT NULL COMMENT '点赞次数',

    vip_article   TINYINT  DEFAULT 0                 NOT NULL COMMENT 'VIP文章：0-普通文章 1-VIP文章',
    audit_status  TINYINT  DEFAULT 0                 NOT NULL COMMENT '审核状态：0-未审核 1-审核通过 2-审核不通过',
    sort          INT(10)  DEFAULT 0                 NOT NULL COMMENT '排序字段',

    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除：0-未删 1-已删'
) COMMENT '文章';

-- 文章类别
CREATE TABLE IF NOT EXISTS lodi.t_category
(
    id            BIGINT                             NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
    name          VARCHAR(255)                       NULL COMMENT '类别名称',
    content       VARCHAR(255)                       NULL COMMENT '类别简介',
    sort          INT(10)                            NOT NULL DEFAULT 0 COMMENT '排序字段，越大越靠前',
    click_count   INT(10)                            NOT NULL DEFAULT 0 COMMENT '点击数',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除：0-未删 1-已删'
) COMMENT '文章类别';

-- 标签
CREATE TABLE IF NOT EXISTS lodi.t_tags
(
    id          BIGINT                             NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
    name        VARCHAR(255)                       NULL COMMENT '标签名称',
    content     VARCHAR(255)                       NULL COMMENT '标签简介',
    sort        INT(10)                            NOT NULL DEFAULT 0 COMMENT '排序字段，越大越靠前',
    click_count INT(10)                            NOT NULL DEFAULT 0 COMMENT '点击数',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除：0-未删 1-已删'
) COMMENT '标签';

-- 动漫信息     # 主要角色
CREATE TABLE IF NOT EXISTS lodi.t_anime_info
(
    id                 BIGINT                                  NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
    series_id          BIGINT                                  NOT NULL COMMENT '系列ID',
    anime_name         VARCHAR(256)                            NOT NULL COMMENT '番名',
    region             VARCHAR(255)                            NULL COMMENT '地区',
    anime_type         VARCHAR(50)                             NULL COMMENT '动漫种类（TV、剧场）',
    season_number      VARCHAR(50)                             NULL COMMENT '季度编号',
    anime_genre        VARCHAR(255)                            NULL COMMENT '动漫类型',
    first_air_date     DATE                                    NULL COMMENT '首播时间',
    play_urls          JSON                                    NULL COMMENT '播放地址（LIST）',
    highlight_images   JSON                                    NULL COMMENT '名场面图片（LIST）',
    cover_url          VARCHAR(255)                            NULL COMMENT '封面地址',
    author             VARCHAR(255)                            NULL COMMENT '作者',
    production_company VARCHAR(255)                            NULL COMMENT '制作公司',
    voice_actors       JSON                                    NULL COMMENT '配音演员列表（LIST）',
    synopsis           TEXT                                    NULL COMMENT '剧情介绍',
    airing_status      TINYINT       DEFAULT 0                 NOT NULL COMMENT '播放状态：0-未放映、1-正在更新、2-已完结',
    total_episodes     INT           DEFAULT 0                 NOT NULL COMMENT '总集数',
    current_episode    INT           DEFAULT 0                 NOT NULL COMMENT '当前集数',
    followers_count    INT           DEFAULT 0                 NOT NULL COMMENT '追番人数',
    rating             DECIMAL(3, 1) DEFAULT 10.0              NOT NULL COMMENT '评分',
    create_time        DATETIME      DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time        DATETIME      DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete          TINYINT       DEFAULT 0                 NOT NULL COMMENT '是否删除：0-未删 1-已删'
) comment '动漫信息';

-- 评论
CREATE TABLE IF NOT EXISTS lodi.t_comment
(
    id               BIGINT                             NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
    first_comment_id BIGINT                             NULL COMMENT '该条评论下的一级评论ID',
    to_id            BIGINT                             NULL COMMENT '回复某条评论的ID',
    article_id       BIGINT                             NULL COMMENT '文章ID',
    user_id          BIGINT                             NOT NULL COMMENT '用户ID',
    to_user_id       BIGINT                             NULL COMMENT '回复某个人的ID',
    content          VARCHAR(2048)                      NOT NULL COMMENT '评论内容',
    source           VARCHAR(255)                       NOT NULL COMMENT '评论来源: ARTICLE, MESSAGE_BOARD, ABOUT',
    create_time      DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time      DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete        TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除：0-未删 1-已删'
) comment '评论';


-- 导航
CREATE TABLE IF NOT EXISTS lodi.t_navigate
(
    id              BIGINT                             NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
    title           VARCHAR(200)                       NOT NULL COMMENT '标题',
    summary         VARCHAR(200)                       NULL COMMENT '简介',
    content         VARCHAR(2048)                      NULL COMMENT '内容',
    url             VARCHAR(512)                       NOT NULL COMMENT 'URL',
    favicon_url     VARCHAR(255)                       NULL COMMENT '网站图标路径',
    nav_category_id BIGINT                             NOT NULL COMMENT '导航类型ID',
    click_count     INT      DEFAULT 0                 NOT NULL COMMENT '点击数',
    sort            INT      DEFAULT 0                 NOT NULL COMMENT '排序字段',
    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time     DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete       TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除：0-未删 1-已删'
) COMMENT '导航';

-- 导航分类
CREATE TABLE IF NOT EXISTS lodi.t_nav_category
(
    id          BIGINT                             NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
    name        VARCHAR(255)                       NULL COMMENT '分类名',
    content     VARCHAR(255)                       NULL COMMENT '分类简介',
    click_count INT      DEFAULT 0                 NOT NULL COMMENT '点击数',
    sort        INT      DEFAULT 0                 NOT NULL COMMENT '排序字段',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除：0-未删 1-已删'
) COMMENT '导航分类表';

-- 文件管理
CREATE TABLE IF NOT EXISTS lodi.t_file_manager
(
    id            BIGINT                             NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
    parent_id     BIGINT DEFAULT 0                   NOT NULL COMMENT '父ID目录关联',
    file_name     VARCHAR(100)                       NOT NULL COMMENT '文件名称',
    file_path     VARCHAR(255)                       NOT NULL COMMENT '文件地址',
    file_type     TINYINT DEFAULT 0                  NOT NULL COMMENT '文件类型：0-目录 1-文件',
    file_size     BIGINT DEFAULT 0                   NULL COMMENT '文件大小',
    file_hash     CHAR(64)                           NULL COMMENT '文件内容哈希',
    created_by    TINYINT                            NOT NULL COMMENT '创建人',
    updated_by    TINYINT                            NOT NULL COMMENT '更新人',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除：0-未删 1-已删'
) comment '文件管理';