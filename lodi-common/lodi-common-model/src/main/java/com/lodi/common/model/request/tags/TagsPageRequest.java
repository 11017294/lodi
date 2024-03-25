package com.lodi.common.model.request.tags;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 标签分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Data
@Schema(title = "标签分页查询 请求体")
public class TagsPageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @Schema(title = "标签名称", description = "标签名称")
    private String name;

    @Schema(title = "标签简介", description = "标签简介")
    private String content;

    @Schema(title = "排序字段，越大越靠前", description = "排序字段，越大越靠前")
    private Integer sort;

    @Schema(title = "点击数", description = "点击数")
    private Integer clickCount;
}
