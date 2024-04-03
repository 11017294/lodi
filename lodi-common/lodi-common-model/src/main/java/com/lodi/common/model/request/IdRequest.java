package com.lodi.common.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * Id 请求体
 *
 * @author MaybeBin
 * @createDate 2023-10-26
 */
@Data
@Schema(description = "Id 请求体")
public class IdRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Id 不能为空")
    @Positive
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10000")
    private Long id;

}
