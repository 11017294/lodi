package com.lodi.common.model.request.userAuth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录请求体
 * @author MaybeBin
 * @createDate 2023-10-24
 */
@Data
@Schema(title = "登录请求体")
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "用户名", description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    private String username;

    @Schema(title = "密码", description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    private String password;

}
