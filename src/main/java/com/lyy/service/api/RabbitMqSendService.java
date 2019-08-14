package com.lyy.service.api;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by liyueyang on 2019/6/5.
 */
public interface RabbitMqSendService extends RabbitTemplate.ConfirmCallback {

    /**
     * 发送消息方法
     *
     * @param message 发送内容
     * @param sign    发送标识，用于区分消息类型
     */
    void sendMessage(Object message, String sign);
}
