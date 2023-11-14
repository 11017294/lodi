package com.lodi.common.security.annotation;

import com.lodi.common.security.enums.Logical;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限认证：必须具有指定权限标识才能进入该方法
 *
 * @author MaybeBin
 * @createDate 2023-11-14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RequiresPermissions {

    /**
     * 权限标识，多个用逗号分隔
     *
     * @return
     */
    String[] value() default {};

    /**
     * 验证逻辑：AND | OR，默认AND
     *
     * @return
     */
    Logical logical() default Logical.AND;

}
