package com.lodi.common.core.web.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * Entity基类
 * @author MaybeBin
 * @createDate 2023-09-20
 */
@Data
public class BaseEntity<T extends Model> extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除：0-未删 1-已删
     */
    @TableLogic
    private Integer isDelete;

}