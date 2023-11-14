package com.lodi.common.security.aspect;

import com.lodi.common.security.annotation.RequiresPermissions;
import com.lodi.common.security.annotation.RequiresRoles;
import com.lodi.common.security.auth.AuthUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 基于 Spring Aop 的注解鉴权
 *
 * @author MaybeBin
 * @createDate 2023-11-14
 */
@Aspect
@Component
public class PreAuthorizeAspect {

    @Pointcut("@annotation(com.lodi.common.security.annotation.RequiresPermissions) || " +
            "@annotation(com.lodi.common.security.annotation.RequiresRoles)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        checkMethodAnnotation(signature.getMethod());
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        }
    }

    public void checkMethodAnnotation(Method method) {
        RequiresRoles requiresRoles = method.getAnnotation(RequiresRoles.class);
        if (requiresRoles != null) {
            AuthUtil.checkRole(requiresRoles);
        }

        // 校验 @RequiresPermissions 注解
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions != null) {
            AuthUtil.checkPermission(requiresPermissions);
        }
    }

}
