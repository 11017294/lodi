package com.lodi.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lodi.common.core.web.domain.BaseEntity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章类别
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Data
@Accessors(chain = true)
@TableName("t_category")
public class Category extends BaseEntity<Category> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 类别名称
    */
    private String name;

    /**
    * 类别简介
    */
    private String content;

    /**
    * 排序字段，越大越靠前
    */
    private Integer sort;

    /**
    * 点击数
    */
    private Integer clickCount;
}
