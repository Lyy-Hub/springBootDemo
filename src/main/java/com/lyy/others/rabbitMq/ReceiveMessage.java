package com.lyy.others.rabbitMq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by liyueyang on 2019/6/5.
 */
@Component
@Slf4j
public class ReceiveMessage {

    @RabbitListener(queues = Constants.QUEUE_NAME)
    public void receiveMessage(Object userName) {
        log.info("消息接收成功，内容为：" + userName);
    }

    @RabbitListener(queues = Constants.USER_DELETE_QUEUE_NAME)
    public void userDeleteReceiveMessage(Object message) {
        log.info("（用户删除）消息接收成功，内容为：" + message);
    }
}
