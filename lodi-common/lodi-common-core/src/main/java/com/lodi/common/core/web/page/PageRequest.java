package com.lodi.common.core.web.page;

import lombok.Data;

import java.io.Serializable;

import static com.lodi.common.core.constant.CommonConstant.SORT_ORDER_ASC;

/**
 * 分页 请求体
 *
 * @author MaybeBin
 * @createDate 2023-10-27
 */
@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页号
     */
    private long current = 1;

    /**
     * 页面大小
     */
    private long pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = SORT_ORDER_ASC;

}
