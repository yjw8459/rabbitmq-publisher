package com.rabbitmq.rabbitmqpublisher.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitResource {

    private static final String EXCHANGE_NAME = "sample.exchange";

    private final RabbitTemplate rabbitTemplate;

    public RabbitResource(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/sample/queue")
    public String publish() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "sample.jwtoo#", "RabbitMQ + Springboot = Success!");
        return "message sending!";
    }
}
