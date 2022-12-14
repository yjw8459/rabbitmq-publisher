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
        // rabbitMQ Customer Send
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "sample.jwtoo", "RabbitMQ + Springboot = Success!");
        return "message sending!";
    }

    @GetMapping("/test/queue")
    public String callTest(){
        rabbitTemplate.convertAndSend("test.exchange", "test.routeKey", "[Test1] RabbitMQ + Springboot = Success!");
        return "Test Message Sending!";
    }
}
