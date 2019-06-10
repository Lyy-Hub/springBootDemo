package com.lyy.rabbitMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by liyueyang on 2019/6/5.
 */
@Component
public class ReceiveMessage {

    private static Logger logger = LoggerFactory.getLogger(ReceiveMessage.class);

    @RabbitListener(queues = Constants.QUEUE_NAME)
    public void receiveMessage(Object userName) {
        logger.info("消息接收成功，内容为：" + userName);
    }

    @RabbitListener(queues = Constants.USER_DELETE_QUEUE_NAME)
    public void userDeleteReceiveMessage(Object message) {
        logger.info("（用户删除）消息接收成功，内容为：" + message);
    }
}
