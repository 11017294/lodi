package com.lodi.common.mybatis.page;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.io.Serializable;

import static com.lodi.common.mybatis.constant.CommonConstant.SORT_ORDER_ASC;

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
    @Min(value = 1, message = "当前页号不能小于1")
    private long currentPage = 1;

    /**
     * 页面大小
     */
    @Range(min = 1, max = 500, message = "页面大小在1-500之间")
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
