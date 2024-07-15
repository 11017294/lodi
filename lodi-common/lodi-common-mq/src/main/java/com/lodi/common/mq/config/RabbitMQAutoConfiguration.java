package com.lodi.common.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import static com.lodi.common.core.constant.MQConstant.*;

/**
 * 消息队列配置类
 *
 * @author MaybeBin
 * @createDate 2024-07-10
 */
@AutoConfiguration
public class RabbitMQAutoConfiguration {

    /**
     * ======================== 交换机
     */

    @Bean(EXCHANGE_DIRECT)
    public Exchange exchangeDirect() {
        // durable：持久化
        return ExchangeBuilder.directExchange(EXCHANGE_DIRECT).durable(true).build();
    }

    /**
     * ======================== 队列
     */

    @Bean(LODI_EMAIL)
    public Queue lodiEmail(){
        return new Queue(LODI_EMAIL);
    }

    @Bean(LODI_SMS)
    public Queue lodiSms(){
        return new Queue(LODI_SMS);
    }

    /**
     * ======================== 绑定队列
     */

    @Bean
    public Binding bindingQueueInformEmail(@Qualifier(LODI_EMAIL) Queue queue, @Qualifier(EXCHANGE_DIRECT) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_EMAIL).noargs();
    }

    @Bean
    public Binding bindingQueueInformSms(@Qualifier(LODI_SMS) Queue queue, @Qualifier(EXCHANGE_DIRECT) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_SMS).noargs();
    }

}
