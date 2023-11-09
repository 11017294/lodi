package com.lodi.common.core.holder;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.lodi.common.core.system.LoginUser;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static com.lodi.common.core.constant.CommonConstant.*;
import static com.lodi.common.core.constant.ContextConstant.LOGIN_USER;

/**
 * 获取当前线程变量中的 用户id、用户名称、Token等信息
 * 注意： 必须在网关通过请求头的方法传入，同时在HeaderInterceptor拦截器设置值。 否则这里无法获取
 *
 * @author MaybeBin
 * @createDate 2023-11-14
 */
public class SecurityContextHolder {

    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, Object> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public static void set(String key, Object value) {
        Object obj = Objects.isNull(value) ? EMPTY : value;
        getLocalMap().put(key, obj);
    }

    public static Object get(String key) {
        return getLocalMap().getOrDefault(key, EMPTY);
    }

    public static <T> T get(String key, Class<T> clazz) {
        return clazz.cast(getLocalMap().getOrDefault(key, EMPTY));
    }

    public static Long getUserId() {
        return get(USER_ID, Long.class);
    }

    public static void setUserId(Long userId) {
        set(USER_ID, userId);
    }

    public static String getUsername() {
        return get(USERNAME, String.class);
    }

    public static void setUsername(String username) {
        set(USERNAME, username);
    }

    public static LoginUser getLoginUser() {
        return get(LOGIN_USER, LoginUser.class);
    }

    public static void setLoginUser(LoginUser loginUser) {
        set(LOGIN_USER, loginUser);
    }

}
