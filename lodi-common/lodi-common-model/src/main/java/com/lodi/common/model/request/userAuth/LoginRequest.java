package com.lodi.common.model.request.userAuth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录请求体
 *
 * @author MaybeBin
 * @createDate 2023-10-24
 */
@Data
@Schema(title = "登录请求体")
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "用户名", description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(title = "密码", description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(title = "登录类型", description = "登录类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "password")
    @NotBlank(message = "登录类型不能为空")
    private String loginType;

}
