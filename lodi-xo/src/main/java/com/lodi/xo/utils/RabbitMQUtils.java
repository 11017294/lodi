package com.lodi.xo.utils;

import com.lodi.common.core.constant.MQConstant;
import com.lodi.common.model.dto.MailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.lodi.common.core.constant.EmailTemplateConstant.AUTH_CODE_TEMPLATE;

/**
 * 用于RabbitMQ发送消息
 *
 * @author MaybeBin
 * @createDate 2024-07-11
 */
@Slf4j
@Component
@RefreshScope
public class RabbitMQUtils {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送邮件
     *
     * @param receiver 接收者
     * @param text     邮件内容
     * @param subject  邮件主题
     */
    private void sendEmail(String receiver, String text, String subject) {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setReceiver(receiver);
        mailDTO.setContent(text);
        mailDTO.setSubject(subject);

        //发送到RabbitMq
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_DIRECT, MQConstant.LODI_EMAIL, mailDTO);
    }

    /**
     * 发送验证码邮件
     *
     * @param receiver 接收者
     * @param code     验证码
     */
    public void sendAuthCodeEmail(String receiver, String code) {
        sendEmail(receiver, String.format(AUTH_CODE_TEMPLATE, code), "LODI验证码");
    }

}
