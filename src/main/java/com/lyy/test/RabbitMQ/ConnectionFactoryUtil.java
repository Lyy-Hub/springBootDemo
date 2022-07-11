package com.lyy.test.RabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;

/**
 * Created by liYueYang on 2022/3/22.
 */
public class ConnectionFactoryUtil {
    public static void main(String[] args) {
        Publisher(); // 推送消息

        Consumer(); // 消费消息
    }

    /**
     * 推送消息
     */
    public static void Publisher() {
        // 创建一个连接
        Connection conn = ConnectionFactoryUtil.GetRabbitConnection();
        if (conn != null) {
            try {
                // 创建通道
                Channel channel = conn.createChannel();
                // 声明队列【参数说明：参数一：队列名称，参数二：是否持久化；参数三：是否独占模式；参数四：消费者断开连接时是否删除队列；参数五：消息其他参数】
                channel.queueDeclare("lyy", true, false, false, null);
                String content = String.format("当前时间：%s", System.currentTimeMillis());
                // 开启发送方确认模式
                channel.confirmSelect();
                // 发送内容【参数说明：参数一：交换机名称；参数二：队列名称，参数三：消息的其他属性-routing headers，此属性为MessageProperties.PERSISTENT_TEXT_PLAIN用于设置纯文本消息存储到硬盘；参数四：消息主体】
                channel.basicPublish("", "lyy", MessageProperties.PERSISTENT_TEXT_PLAIN, content.getBytes("UTF-8"));
                if (channel.waitForConfirms()) {
                    System.out.println("消息发送成功" );
                }
                System.out.println("已发送消息：" + content);
                // 关闭连接
                channel.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费消息
     */
    public static void Consumer() {
        // 创建一个连接
        Connection conn = ConnectionFactoryUtil.GetRabbitConnection();
        if (conn != null) {
            try {
                // 创建通道
                Channel channel = conn.createChannel();
                // 声明队列【参数说明：参数一：队列名称，参数二：是否持久化；参数三：是否独占模式；参数四：消费者断开连接时是否删除队列；参数五：消息其他参数】
                channel.queueDeclare("lyy", true, false, false, null);

                // 创建订阅器，并接受消息
                channel.basicConsume("lyy", false, "", new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                               byte[] body) throws IOException {
                        String routingKey = envelope.getRoutingKey(); // 队列名称
                        String contentType = properties.getContentType(); // 内容类型
                        String content = new String(body, "utf-8"); // 消息正文
                        System.out.println("消息正文：" + content);
                        //channel.basicNack( envelope.getDeliveryTag(),false, true);
                        channel.basicAck(envelope.getDeliveryTag(), false); // 手动确认消息【参数说明：参数一：该消息的index；参数二：是否批量应答，true批量确认小于index的消息】
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection GetRabbitConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("ZYGIS");
        factory.setPassword("ZYGIS");
        factory.setVirtualHost("/");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        Connection conn = null;
        try {
            conn = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
