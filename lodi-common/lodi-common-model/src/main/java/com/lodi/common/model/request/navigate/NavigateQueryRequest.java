package com.lodi.common.model.request.navigate;

import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 导航分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Data
@Schema(description = "导航分页查询 请求体")
public class NavigateQueryRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @Schema(description = "导航类型id")
    private Long navCategoryId;

}
