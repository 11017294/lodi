package com.lodi.sms.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
 *
 * @author MaybeBin
 * @createDate 2024-07-11
 */
@Slf4j
@Component
public class SendMailUtils {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    /**
     * 发送邮件
     *
     * @param subject  主题
     * @param receiver 接收者
     * @param text     邮件内容
     */
    public void sendMail(String subject, String receiver, String text) {
        try {
            //创建一个复杂的消息邮件
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            // 邮件主题
            helper.setSubject(subject);
            // 邮件内容
            helper.setText(text, true);
            //邮件接收人
            helper.setTo(receiver);
            //邮件发送者
            helper.setFrom(sender);

            // 发送邮件
            mailSender.send(mimeMessage);

            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
