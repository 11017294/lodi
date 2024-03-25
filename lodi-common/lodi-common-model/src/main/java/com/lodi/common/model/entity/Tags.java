package com.lodi.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lodi.common.mybatis.domain.BaseEntity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 标签
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Data
@Accessors(chain = true)
@TableName("t_tags")
public class Tags extends BaseEntity<Tags> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 标签名称
    */
    private String name;

    /**
    * 标签简介
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
