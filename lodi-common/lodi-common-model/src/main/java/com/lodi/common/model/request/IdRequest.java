package com.lodi.common.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * Id 请求体
 * @author MaybeBin
 * @createDate 2023-10-26
 */
@Data
@Schema(title = "Id 请求体")
public class IdRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10000", description = "Id")
    private Long id;

}
