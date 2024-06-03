package com.lodi.common.satoken.config;

import cn.dev33.satoken.stp.StpInterface;
import com.lodi.common.satoken.core.service.StpInterfaceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sa-Token 配置
 *
 * @author MaybeBin
 * @createDate 2024-05-27
 */
@Configuration
public class SaTokenConfigure {

    /**
     * 权限接口实现(使用bean注入方便用户替换)
     */
    @Bean
    public StpInterface stpInterface() {
        return new StpInterfaceImpl();
    }

}
