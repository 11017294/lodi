package com.lodi.common.doc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger 配置属性
 *
 * @author MaybeBin
 * @createDate 2023-09-22
 */
@Data
@ConfigurationProperties("lodi.swagger")
public class SwaggerProperties {

    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 作者
     */
    private String author;
    /**
     * 版本
     */
    private String version;
    /**
     * url
     */
    private String url;
    /**
     * email
     */
    private String email;

}
