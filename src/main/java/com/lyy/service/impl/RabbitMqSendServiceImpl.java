package com.lyy.service.impl;

import com.lyy.rabbitMq.Constants;
import com.lyy.service.api.RabbitMqSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by liyueyang on 2019/6/5.
 */
@Component
public class RabbitMqSendServiceImpl implements RabbitMqSendService {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqSendServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Object message) {
        //设置回调对象
        rabbitTemplate.setConfirmCallback(this);
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.QUEUE_ROUTE_KEY, message, correlationData);
        logger.info("发送消息到RabbitMQ, 消息内容: " + message);
    }

    /**
     * 消息回调确认方法
     *
     * @param correlationData 回调数据
     * @param isSendSuccess   是否发送成功
     * @param
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String s) {
        logger.info("confirm回调消息ID为: " + correlationData.getId());
        if (isSendSuccess) {
            logger.info("confirm回调消息发送成功");
        } else {
            logger.info("confirm回调消息发送失败" + s);
        }
    }
}
