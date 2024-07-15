package com.lodi.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消息服务
 *
 * @author MaybeBin
 * @createDate 2024-07-11
 */
@SpringBootApplication
public class SmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ  sms服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                        " _                   _   _      \n" +
                        "| |       ___     __| | (_)     \n" +
                        "| |      / _ \\   / _` | | |    \n" +
                        "| |___  | (_) | | (_| | | |     \n" +
                        "|_____|  \\___/   \\__,_| |_|   \n");
    }

}
