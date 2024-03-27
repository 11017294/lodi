-- region 注意事项

/**
 *
 * 注意自己的nacos版本，当前sql来源于nacos 2.2.3版本，旧一些的版本可能会缺少某些字段
 * 1、如果nacos无法启动请查看nacos的日志
 * 2、需要修改nacos安装目录下 nacos\conf\application.properties 文件，增加数据库的配置
 *
 */

-- endregion

-- region 创建 nacos_config 数据库

CREATE DATABASE IF NOT EXISTS `nacos_config` DEFAULT CHARACTER SET utf8;

USE `nacos_config`;

-- endregion

-- region 创建 nacos 相关表

/**
 * 表名称 = config_info
 */
CREATE TABLE `config_info` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `data_id` varchar(255) NOT NULL COMMENT 'data_id',
                               `group_id` varchar(128) DEFAULT NULL,
                               `content` longtext NOT NULL COMMENT 'content',
                               `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
                               `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                               `src_user` text COMMENT 'source user',
                               `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
                               `app_name` varchar(128) DEFAULT NULL,
                               `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
                               `c_desc` varchar(256) DEFAULT NULL,
                               `c_use` varchar(64) DEFAULT NULL,
                               `effect` varchar(64) DEFAULT NULL,
                               `type` varchar(64) DEFAULT NULL,
                               `c_schema` text,
                               `encrypted_data_key` text NOT NULL COMMENT '秘钥',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

/**
 * 表名称 = config_info_aggr
 */
CREATE TABLE `config_info_aggr` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `data_id` varchar(255) NOT NULL COMMENT 'data_id',
                                    `group_id` varchar(128) NOT NULL COMMENT 'group_id',
                                    `datum_id` varchar(255) NOT NULL COMMENT 'datum_id',
                                    `content` longtext NOT NULL COMMENT '内容',
                                    `gmt_modified` datetime NOT NULL COMMENT '修改时间',
                                    `app_name` varchar(128) DEFAULT NULL,
                                    `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

/**
 * 表名称 = config_info_beta
 */
CREATE TABLE `config_info_beta` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `data_id` varchar(255) NOT NULL COMMENT 'data_id',
                                    `group_id` varchar(128) NOT NULL COMMENT 'group_id',
                                    `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
                                    `content` longtext NOT NULL COMMENT 'content',
                                    `beta_ips` varchar(1024) DEFAULT NULL COMMENT 'betaIps',
                                    `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
                                    `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                    `src_user` text COMMENT 'source user',
                                    `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
                                    `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
                                    `encrypted_data_key` text NOT NULL COMMENT '秘钥',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

/**
 * 表名称 = config_info_tag
 */
CREATE TABLE `config_info_tag` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `data_id` varchar(255) NOT NULL COMMENT 'data_id',
                                   `group_id` varchar(128) NOT NULL COMMENT 'group_id',
                                   `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
                                   `tag_id` varchar(128) NOT NULL COMMENT 'tag_id',
                                   `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
                                   `content` longtext NOT NULL COMMENT 'content',
                                   `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
                                   `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                   `src_user` text COMMENT 'source user',
                                   `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

/**
 * 表名称 = config_tags_relation
 */
CREATE TABLE `config_tags_relation` (
                                        `id` bigint(20) NOT NULL COMMENT 'id',
                                        `tag_name` varchar(128) NOT NULL COMMENT 'tag_name',
                                        `tag_type` varchar(64) DEFAULT NULL COMMENT 'tag_type',
                                        `data_id` varchar(255) NOT NULL COMMENT 'data_id',
                                        `group_id` varchar(128) NOT NULL COMMENT 'group_id',
                                        `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
                                        `nid` bigint(20) NOT NULL AUTO_INCREMENT,
                                        PRIMARY KEY (`nid`),
                                        UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
                                        KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

/**
 * 表名称 = group_capacity
 */
CREATE TABLE `group_capacity` (
                                  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `group_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
                                  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
                                  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
                                  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
                                  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
                                  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
                                  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
                                  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

/**
 * 表名称 = his_config_info
 */
CREATE TABLE `his_config_info` (
                                   `id` bigint(20) unsigned NOT NULL,
                                   `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                   `data_id` varchar(255) NOT NULL,
                                   `group_id` varchar(128) NOT NULL,
                                   `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
                                   `content` longtext NOT NULL,
                                   `md5` varchar(32) DEFAULT NULL,
                                   `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `src_user` text,
                                   `src_ip` varchar(50) DEFAULT NULL,
                                   `op_type` char(10) DEFAULT NULL,
                                   `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
                                   `encrypted_data_key` text NOT NULL COMMENT '秘钥',
                                   PRIMARY KEY (`nid`),
                                   KEY `idx_gmt_create` (`gmt_create`),
                                   KEY `idx_gmt_modified` (`gmt_modified`),
                                   KEY `idx_did` (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

/**
 * 表名称 = tenant_capacity
 */
CREATE TABLE `tenant_capacity` (
                                   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                   `tenant_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Tenant ID',
                                   `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
                                   `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
                                   `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
                                   `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
                                   `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
                                   `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
                                   `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

/**
 * 表名称 = tenant_info
 */
CREATE TABLE `tenant_info` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `kp` varchar(128) NOT NULL COMMENT 'kp',
                               `tenant_id` varchar(128) default '' COMMENT 'tenant_id',
                               `tenant_name` varchar(128) default '' COMMENT 'tenant_name',
                               `tenant_desc` varchar(256) DEFAULT NULL COMMENT 'tenant_desc',
                               `create_source` varchar(32) DEFAULT NULL COMMENT 'create_source',
                               `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
                               `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
                               KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

/**
 * 表名称 = users
 */
CREATE TABLE `users` (
                         `username` varchar(50) NOT NULL PRIMARY KEY,
                         `password` varchar(500) NOT NULL,
                         `enabled` boolean NOT NULL
);

/**
 * 表名称 = roles
 */
CREATE TABLE `roles` (
                         `username` varchar(50) NOT NULL,
                         `role` varchar(50) NOT NULL,
                         UNIQUE INDEX `idx_user_role` (`username` ASC, `role` ASC) USING BTREE
);

/**
 * 表名称 = permissions
 */
CREATE TABLE `permissions` (
                               `role` varchar(50) NOT NULL,
                               `resource` varchar(255) NOT NULL,
                               `action` varchar(8) NOT NULL,
                               UNIQUE INDEX `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
);

INSERT INTO users (username, password, enabled) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', TRUE);

INSERT INTO roles (username, role) VALUES ('nacos', 'ROLE_ADMIN');

-- endregion

-- region nacos cofig 相关配置
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key) VALUES (135, 'lodi-web', 'dev', '#app
server:
  port: 9528

spring:
  application:
    name: lodi-web
  # 邮箱配置
  mail:
    # 平台地址(当前为qq邮箱)
    host: smtp.qq.com
    # 端口号
    port: 465
    # 发送邮件的邮箱地址：改成自己的邮箱
    username: 11017294@qq.com
    # 发送短信后它给你的授权码 填写到这里
    password:
    # 开启ssl 否则 503 错误
    properties:
      mail:
        smtp:
          ssl:
            enable: true
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/lodi?characterEncoding=utf8&serverTimezone=UTC
    username: root
    password:
    type: com.alibaba.druid.pool.DruidDataSource
    # 连接池配置：
    druid:
      initial-size: 2 # 初始化时建立物理连接的个数。默认0
      max-active: 10 # 最大连接池数量，默认8
      min-idle: 1 # 最小连接池数量
      max-wait: 2000 # 获取连接时最大等待时间，单位毫秒。
      pool-prepared-statements: false # 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
      max-pool-prepared-statement-per-connection-size: -1 # 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
      # ……druid节点下的其它参数见官方文档：https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8

      # 启用Druid内置的Filter，会使用默认的配置。可自定义配置，见下方的各个filter节点。
      filters: stat,wall

      # StatViewServlet监控器。开启后，访问http://域名/druid/index.html
      stat-view-servlet:
        enabled: true # 开启 StatViewServlet，即开启监控功能
        login-username: admin # 访问监控页面时登录的账号
        login-password: 123456 # 密码
        url-pattern: /druid/* # Servlet的映射地址，不填写默认为"/druid/*"。如填写其它地址，访问监控页面时，要使用相应的地址
        reset-enable: false # 是否允许重置数据（在页面的重置按钮）。（停用后，依然会有重置按钮，但重置后不会真的重置数据）
#        allow: 192.168.1.2,192.168.1.1 # 监控页面访问白名单。默认为127.0.0.1。与黑名单一样，支持子网掩码，如128.242.127.1/24。多个ip用英文逗号分隔
        deny: 18.2.1.3 # 监控页面访问黑名单

      # 配置 WebStatFilter（StatFilter监控器中的Web模板）
      web-stat-filter:
        enabled: true # 开启 WebStatFilter，即开启监控功能中的 Web 监控功能
        url-pattern: /* # 映射地址，即统计指定地址的web请求
        exclusions: \'*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*\' # 不统计的web请求，如下是不统计静态资源及druid监控页面本身的请求
        session-stat-enable: true # 是否启用session统计
        session-stat-max-count: 1 # session统计的最大个数，默认是1000。当统计超过这个数，只统计最新的
        principal-session-name: userName # 所存用户信息的serssion参数名。Druid会依照此参数名读取相应session对应的用户名记录下来（在监控页面可看到）。如果指定参数不是基础数据类型，将会自动调用相应参数对象的toString方法来取值
        principal-cookie-name: userName # 与上类似，但这是通过Cookie名取到用户信息
        profile-enable: true # 监控单个url调用的sql列表（试了没生效，以后需要用再研究）

      filter:
        wall:
          enabled: true  # 开启SQL防火墙功能
          config:
            select-allow: true # 允许执行Select查询操作
            delete-allow: false # 不允许执行delete操作
            create-table-allow: false # 不允许创建表
            # 更多用法，参考官方文档：https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE-wallfilter

#mybatis配置
mybatis-plus:
  mapper-locations: classpath:/mapper/*.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  #日志输出配置
  global-config:
    db-config:
      logic-delete-field: is_delete  #全局逻辑删除字段值
      logic-delete-value: 1 #逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 #逻辑未删除值(默认为 0)

# springdoc-openapi项目配置
springdoc:
  api-docs:
    enabled: true # 是否开启接口文档
  swagger-ui:
    enabled: true # 2.1 是否开启 Swagger 文档的官方 UI 界面
    path: /swagger-ui.html
    persistAuthorization: true # 持久化认证数据，如果设置为 true，它会保留授权数据并且不会在浏览器关闭/刷新时丢失
  default-flat-param-object: false # 有bug 参见 https://doc.xiaominfo.com/docs/faq/v4/knife4j-parameterobject-flat-param 文档
# knife4j的增强配置，不需要增强可以不配
knife4j:
  enable: true
  setting:
    language: zh_cn

lodi:
  info:
    version: 1.0.0
  swagger:
    title: 博客
    description: 提供博客用户访问的所有功能
    author: MaybeBin
    version: ${lodi.info.version}
    url: https://github.com/11017294
    email: 11017294@qq.com
', '542f10cbdc72fbc7af2084385850434b', '2023-09-26 10:24:09', '2024-03-27 09:07:46', null, '127.0.0.1', '', 'dev', '', '', '', 'yaml', '', '');
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key) VALUES (137, 'lodi-admin', 'dev', '#app
server:
  port: 9527

spring:
  application:
    name: lodi-admin
    # 邮箱配置
  mail:
    # 平台地址(当前为qq邮箱)
    host: smtp.qq.com
    # 端口号
    port: 465
    # 发送邮件的邮箱地址：改成自己的邮箱
    username: 11017294@qq.com
    # 发送短信后它给你的授权码 填写到这里
    password:
    # 开启ssl 否则 503 错误
    properties:
      mail:
        smtp:
          ssl:
            enable: true
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
  #redis相关配置
  redis:
    host: 127.0.0.1
    port: 6379
    database: 1
    timeout: 300000
    lettuce:
      pool:
        max-active: 20
        max-wait: -1
        #最大阻塞等待时间(负数表示没限制)
        max-idle: 5
        min-idle: 0
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/lodi?characterEncoding=utf8&serverTimezone=UTC
    username: root
    password:
    type: com.alibaba.druid.pool.DruidDataSource
    # 连接池配置：
    druid:
      initial-size: 2 # 初始化时建立物理连接的个数。默认0
      max-active: 10 # 最大连接池数量，默认8
      min-idle: 1 # 最小连接池数量
      max-wait: 2000 # 获取连接时最大等待时间，单位毫秒。
      pool-prepared-statements: false # 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
      max-pool-prepared-statement-per-connection-size: -1 # 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
      # ……druid节点下的其它参数见官方文档：https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8

      # 启用Druid内置的Filter，会使用默认的配置。可自定义配置，见下方的各个filter节点。
      filters: stat,wall

      # StatViewServlet监控器。开启后，访问http://域名/druid/index.html
      stat-view-servlet:
        enabled: true # 开启 StatViewServlet，即开启监控功能
        login-username: admin # 访问监控页面时登录的账号
        login-password: 123456 # 密码
        url-pattern: /druid/* # Servlet的映射地址，不填写默认为"/druid/*"。如填写其它地址，访问监控页面时，要使用相应的地址
        reset-enable: false # 是否允许重置数据（在页面的重置按钮）。（停用后，依然会有重置按钮，但重置后不会真的重置数据）
#        allow: 192.168.1.2,192.168.1.1 # 监控页面访问白名单。默认为127.0.0.1。与黑名单一样，支持子网掩码，如128.242.127.1/24。多个ip用英文逗号分隔
        deny: 18.2.1.3 # 监控页面访问黑名单

      # 配置 WebStatFilter（StatFilter监控器中的Web模板）
      web-stat-filter:
        enabled: true # 开启 WebStatFilter，即开启监控功能中的 Web 监控功能
        url-pattern: /* # 映射地址，即统计指定地址的web请求
        exclusions: \'*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*\' # 不统计的web请求，如下是不统计静态资源及druid监控页面本身的请求
        session-stat-enable: true # 是否启用session统计
        session-stat-max-count: 1 # session统计的最大个数，默认是1000。当统计超过这个数，只统计最新的
        principal-session-name: userName # 所存用户信息的serssion参数名。Druid会依照此参数名读取相应session对应的用户名记录下来（在监控页面可看到）。如果指定参数不是基础数据类型，将会自动调用相应参数对象的toString方法来取值
        principal-cookie-name: userName # 与上类似，但这是通过Cookie名取到用户信息
        profile-enable: true # 监控单个url调用的sql列表（试了没生效，以后需要用再研究）

      filter:
        wall:
          enabled: true  # 开启SQL防火墙功能
          config:
            select-allow: true # 允许执行Select查询操作
            delete-allow: false # 不允许执行delete操作
            create-table-allow: false # 不允许创建表
            # 更多用法，参考官方文档：https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE-wallfilter

#mybatis配置
mybatis-plus:
  mapper-locations: classpath:/mapper/*.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  #日志输出配置
  global-config:
    db-config:
      logic-delete-field: is_delete  #全局逻辑删除字段值
      logic-delete-value: 1 #逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 #逻辑未删除值(默认为 0)

# springdoc-openapi项目配置
springdoc:
  api-docs:
    enabled: true # 是否开启接口文档
  swagger-ui:
    enabled: true # 2.1 是否开启 Swagger 文档的官方 UI 界面
    path: /swagger-ui.html
    persistAuthorization: true # 持久化认证数据，如果设置为 true，它会保留授权数据并且不会在浏览器关闭/刷新时丢失
  default-flat-param-object: false # 有bug 参见 https://doc.xiaominfo.com/docs/faq/v4/knife4j-parameterobject-flat-param 文档
# knife4j的增强配置，不需要增强可以不配
knife4j:
  enable: true
  setting:
    language: zh_cn

lodi:
  info:
    version: 1.0.0
  swagger:
    title: 管理后台
    description: 提供管理员管理的所有功能
    author: MaybeBin
    version: ${lodi.info.version}
    url: https://github.com/11017294
    email: 11017294@qq.com
', '6dd54638e30d148e69a8d525b1def47f', '2023-09-26 10:29:24', '2024-03-27 09:09:43', null, '127.0.0.1', '', 'dev', '', '', '', 'yaml', '', '');
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key) VALUES (151, 'lodi-file', 'dev', 'lodi:
  qiniu-yun:
    accessKey:
    secretKey:
    bucket:
    domain:
    # todo：七牛云地址
  # 图片路径
  picturePath:
', '690044663a3e2177778a0903ac5a611f', '2023-11-20 16:02:53', '2024-03-27 09:10:59', null, '127.0.0.1', '', 'dev', '', '', '', 'yaml', '', '');
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key) VALUES (154, 'lodi-gateway', 'dev', '# 注册中心相关配置
spring:
  #redis相关配置
  redis:
    host: 127.0.0.1
    port: 6379
    database: 1
    timeout: 300000
    lettuce:
      pool:
        max-active: 20
        max-wait: -1
        #最大阻塞等待时间(负数表示没限制)
        max-idle: 5
        min-idle: 0
  cloud:
    gateway:
      routes:
        - id: login
          uri: lb://lodi-admin
          predicates:
            - Path=/lodi-web/auth/**
          filters:
            - StripPrefix=1
        - id: lodi_admin
          uri: lb://lodi-admin  # 路由到 lodi-admin 服务的负载均衡地址
          predicates:
            - Path=/lodi-admin/** # 匹配路径以 /lodi-admin/ 开头的请求
          filters:
            - StripPrefix=1 # 过滤器，移除请求中的前缀路径
        - id: lodi_web
          uri: lb://lodi-web
          predicates:
            - Path=/lodi-web/**
          filters:
            - StripPrefix=1
# 安全配置
security:
  # 不校验白名单
  ignore:
    whites:
      - /lodi-admin/doc.html
      - /lodi-admin/webjars/**
      - /lodi-admin/swagger-resources/**
      - /lodi-admin/swagger-ui.html
      - /lodi-admin/v2/api-docs/**
      - /lodi-admin/v3/api-docs/**
      - /lodi-admin/auth/login
      - /lodi-admin/auth/register
      - /lodi-admin/auth/sendEmailCode
      - /lodi-web/auth/login
      - /lodi-web/auth/register
      - /lodi-web/auth/sendEmailCode
      - /lodi-web/doc.html
      - /lodi-web/webjars/**
      - /lodi-web/swagger-resources/**
      - /lodi-web/swagger-ui.html
      - /lodi-web/v2/api-docs/**
      - /lodi-web/v3/api-docs/**
      - /lodi-web/index/**
      - /lodi-web/tags/**
      - /lodi-web/category/**

# 聚合swagger文档
knife4j:
  gateway:
    enabled: true
    # 排序规则(tag/operation排序自4.2.0版本新增)
    # 取值：alpha-默认排序规则，官方swagger-ui默认实现,order-Knife4j提供的增强排序规则，开发者可扩展x-order，根据数值来自定义排序
    tags-sorter: order
    operations-sorter: order
    strategy: manual  # 指定服务发现的模式聚合微服务文档
    routes:
      - name: 后台管理
        url: /lodi-admin/v3/api-docs?group=all  # 子服务存在其他分组情况，聚合其他分组
        service-name: lodi-admin  # 服务名称(Optional)
        context-path: /lodi-admin # 路由前缀
        order: 2  # 排序
      - name: 博客
        url: /lodi-web/v3/api-docs?group=all
        service-name: lodi-web
        context-path: /lodi-web
        order: 3

logging:
  level:
    org:
      springframework:
        cloud:
          gateway: trace', 'a4894c2ff9b03056c17ebecbb291aa34', '2023-11-30 10:29:17', '2023-12-04 23:31:38', null, '127.0.0.1', '', 'dev', '', '', '', 'yaml', '', '');

-- endregion