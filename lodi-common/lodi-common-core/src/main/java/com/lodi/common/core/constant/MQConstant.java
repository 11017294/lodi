package com.lodi.common.core.constant;

/**
 * 消息队列常量
 *
 * @author MaybeBin
 * @createDate 2024-07-10
 */
public interface MQConstant {

    /**
     * 交换机名
     */
    String EXCHANGE_DIRECT = "exchange.direct";

    /**
     * 队列名
     */
    String LODI_EMAIL = "lodi.email";
    String LODI_SMS = "lodi.sms";

    /**
     * 路由名
     */
    String ROUTING_KEY_EMAIL = "lodi.email";
    String ROUTING_KEY_SMS = "lodi.sms";

}
