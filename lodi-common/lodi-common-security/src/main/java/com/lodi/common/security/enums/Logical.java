package com.lodi.common.security.enums;

/**
 * 权限注解的验证模式
 *
 * @author MaybeBin
 * @createDate 2023-11-14
 */
public enum Logical {

    /**
     * 必须具有所有元素
     */
    AND,

    /**
     * 只需具有一个元素
     */
    OR;

}
