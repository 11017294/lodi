package com.lodi.sms.listener;

import com.lodi.common.core.constant.MQConstant;
import com.lodi.common.model.dto.MailDTO;
import com.lodi.sms.util.SendMailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 邮箱监听器
 *
 * @author MaybeBin
 * @createDate 2024-07-11
 */
@Slf4j
@Component
public class EmailListener {

    @Resource
    private SendMailUtils sendMailUtils;

    @RabbitListener(queues = MQConstant.LODI_EMAIL)
    public void sendMail(MailDTO mailDTO) {
        String subject = mailDTO.getSubject();
        String receiver = mailDTO.getReceiver();
        String content = mailDTO.getContent();
        if (Objects.isNull(subject) || Objects.isNull(receiver) || Objects.isNull(content)) {
            log.error("邮件发送失败，接收者或邮件内容为空");
        }
        sendMailUtils.sendMail(subject, receiver, content);
    }

}
