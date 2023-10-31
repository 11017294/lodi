package com.lodi.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * admin 服务
 * @author MaybeBin
 * @createDate 2023-09-19
 */
@MapperScan("com.lodi.xo.mapper")
@ComponentScan(basePackages = {
        "com.lodi.xo",
        "com.lodi.admin",
        "com.lodi.common.core"
})
@SpringBootApplication
@EnableDiscoveryClient
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ  admin服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " _                   _   _      \n" +
                "| |       ___     __| | (_)     \n" +
                "| |      / _ \\   / _` | | |    \n" +
                "| |___  | (_) | | (_| | | |     \n" +
                "|_____|  \\___/   \\__,_| |_|   \n");
    }
}
