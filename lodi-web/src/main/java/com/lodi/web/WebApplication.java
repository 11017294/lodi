package com.lodi.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.lodi.xo",
        "com.lodi.web"
})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.lodi.api")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ  web服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " _                   _   _      \n" +
                "| |       ___     __| | (_)     \n" +
                "| |      / _ \\   / _` | | |    \n" +
                "| |___  | (_) | | (_| | | |     \n" +
                "|_____|  \\___/   \\__,_| |_|   \n");
    }
}
