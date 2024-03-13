package com.lodi.common.model.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 20, message = "用户名长度为4-20位")
    private String username;

    @Schema(title = "密码", description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度为6-20位")
    private String password;

    @Schema(title = "确认密码", description = "确认密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

    @Schema(title = "邮箱", description = "邮箱", requiredMode = Schema.RequiredMode.REQUIRED, example = "10000@qq.com")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Schema(title = "验证码", description = "验证码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotBlank(message = "验证码不能为空")
    private String code;

}
