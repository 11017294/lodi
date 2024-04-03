package com.lodi.common.model.vo;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 标签 视图
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Data
@Schema(description = "标签 视图")
public class TagsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "标签名称")
    private String name;

    @Schema(description = "标签简介")
    private String content;

    @Schema(description = "排序字段，越大越靠前")
    private Integer sort;

    @Schema(description = "点击数")
    private Integer clickCount;
}
