package com.lodi.common.model.request.category;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 添加文章类别 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Data
@Schema(title = "添加文章类别 请求体")
public class CategoryAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "类别名称", description = "类别名称")
    @NotBlank(message = "类别名称不能为空")
    private String name;

    @Schema(title = "类别简介", description = "类别简介")
    @NotBlank(message = "类别简介不能为空")
    private String content;

    @Schema(title = "排序字段，越大越靠前", description = "排序字段，越大越靠前")
    private Integer sort;

}
