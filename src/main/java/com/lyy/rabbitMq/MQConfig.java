package com.lyy.rabbitMq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liyueyang on 2019/6/5.
 */
@Configuration
public class MQConfig {

    /**
     * 配置交换机实例
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(Constants.EXCHANGE_NAME);
    }

    /**
     * 配置队列实例，并且设置持久化队列
     */
    @Bean
    public Queue queue() {
        return new Queue(Constants.QUEUE_NAME, true);
    }

    /**
     * 将队列绑定到交换机上，并设置消息分发的路由键
     */
    @Bean
    public Binding binding() {
        //链式写法: 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(queue()).to(directExchange()).with(Constants.QUEUE_ROUTE_KEY);
    }
}