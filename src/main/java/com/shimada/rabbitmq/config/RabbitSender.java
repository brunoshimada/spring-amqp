package com.shimada.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitSender {

    private final Queue queue;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitSender(Queue queue, RabbitTemplate rabbitTemplate) {
        this.queue = queue;
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}
