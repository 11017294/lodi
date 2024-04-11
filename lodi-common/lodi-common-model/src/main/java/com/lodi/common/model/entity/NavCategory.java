package com.lodi.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lodi.common.mybatis.domain.BaseEntity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 导航分类表
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Data
@Accessors(chain = true)
@TableName("t_nav_category")
public class NavCategory extends BaseEntity<NavCategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 分类名
    */
    private String name;

    /**
    * 分类简介
    */
    private String content;

    /**
    * 点击数
    */
    private Integer clickCount;

    /**
    * 排序字段
    */
    private Integer sort;
}
