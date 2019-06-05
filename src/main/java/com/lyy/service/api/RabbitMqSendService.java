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
     */
    void sendMessage(Object message);
}
