package com.lodi.common.security.config;

import com.lodi.common.security.interceptor.HeaderInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author MaybeBin
 * @createDate 2023-11-14
 */
public class WebMvcConfig implements WebMvcConfigurer {

    public static final String[] EXCLUDE_PATH = {"/login", "/logout"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATH)
                .order(-10);
    }

    private HeaderInterceptor headerInterceptor() {
        return new HeaderInterceptor();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
