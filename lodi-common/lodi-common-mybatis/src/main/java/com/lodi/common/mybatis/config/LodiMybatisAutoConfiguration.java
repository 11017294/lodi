package com.lodi.common.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lodi.common.mybatis.handler.LodiMetaObjectHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * MyBaits 配置类
 *
 * @author MaybeBin
 * @createDate 2024-06-24
 */
@AutoConfiguration
public class LodiMybatisAutoConfiguration {

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler() {
        return new LodiMetaObjectHandler();
    }

}
