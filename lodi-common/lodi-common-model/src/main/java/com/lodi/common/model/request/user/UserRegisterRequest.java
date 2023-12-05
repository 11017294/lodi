package com.lodi.common.model.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册 请求体
 *
 * @author MaybeBin
 * @createDate 2023-10-26
 */
@Data
@Schema(title = "用户注册 请求体")
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "用户名", description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    private String username;

    @Schema(title = "密码", description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    private String password;

    @Schema(title = "确认密码", description = "确认密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    private String confirmPassword;

    @Schema(title = "邮箱", description = "邮箱", requiredMode = Schema.RequiredMode.REQUIRED, example = "10000@qq.com")
    private String email;

    @Schema(title = "验证码", description = "验证码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String code;

}
