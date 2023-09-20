package com.lodi.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * admin 服务
 * @author MaybeBin
 * @createDate 2023-09-19
 */
@SpringBootApplication
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
