package com.lodi.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lodi.common.mybatis.domain.BaseEntity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 导航
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Data
@Accessors(chain = true)
@TableName("t_navigate")
public class Navigate extends BaseEntity<Navigate> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 标题
    */
    private String title;

    /**
    * 简介
    */
    private String summary;

    /**
    * 内容
    */
    private String content;

    /**
    * url
    */
    private String url;

    /**
    * 网站图标路径
    */
    private String faviconUrl;

    /**
    * 导航类型id
    */
    private Long navCategoryId;

    /**
    * 点击数
    */
    private Integer clickCount;

    /**
    * 排序字段
    */
    private Integer sort;
}
