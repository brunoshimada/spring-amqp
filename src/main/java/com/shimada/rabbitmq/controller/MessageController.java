package com.shimada.rabbitmq.controller;

import com.shimada.rabbitmq.config.RabbitSender;
import com.shimada.rabbitmq.controller.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    private final RabbitSender rabbitSender;

    @Autowired
    public MessageController(RabbitSender rabbitSender) {
        this.rabbitSender = rabbitSender;
    }

    @PostMapping
    public void postarMensagem (@RequestBody Message message) {
        rabbitSender.sendMessage(message.getMessage());
    }

}
