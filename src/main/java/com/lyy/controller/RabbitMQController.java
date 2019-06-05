package com.lyy.controller;

import com.lyy.service.api.RabbitMqSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyueyang on 2019/6/5.
 */
@RestController
@RequestMapping("rabbitMq")
public class RabbitMQController {
    @Autowired
    private RabbitMqSendService sendMessageService;

    @GetMapping("send")
    public String saveUser() {
        //发送消息到RabbitMQ
        sendMessageService.sendMessage("发送消息咯！！！");
        return "success";
    }
}
