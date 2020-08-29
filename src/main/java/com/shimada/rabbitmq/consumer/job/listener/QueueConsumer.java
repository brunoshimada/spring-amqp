package com.shimada.rabbitmq.consumer.job.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Objects;

@Component
public class QueueConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueConsumer.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public QueueConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void scheduledFunction() {
        LOGGER.info(MessageFormat.format("{0} - executou", this.getClass().getSimpleName()));

        final Message receivedMessage = rabbitTemplate.receive("hello-queue");

        if (Objects.nonNull(receivedMessage)) {
            LOGGER.info(MessageFormat.format("Message received -> {0}", receivedMessage.toString()));
        } else {
            LOGGER.info("Sem novas mensagens");
        }
    }
}
