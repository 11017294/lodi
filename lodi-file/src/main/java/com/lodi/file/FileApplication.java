package com.lodi.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 文件服务
 *
 * @author MaybeBin
 * @createDate 2023-11-20
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ  文件服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " _                   _   _      \n" +
                "| |       ___     __| | (_)     \n" +
                "| |      / _ \\   / _` | | |    \n" +
                "| |___  | (_) | | (_| | | |     \n" +
                "|_____|  \\___/   \\__,_| |_|   \n");
    }
}