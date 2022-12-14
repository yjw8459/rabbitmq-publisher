package com.rabbitmq.rabbitmqpublisher.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

/**
 * Publisher Config
 */
public class PublisherConfig {

    private static final String EXCHANGE_NAME = "sample.exchange";

    private static final String QUEUE_NAME = "sample.queue";

    private static final String ROUTING_KEY = "sample.jwtoo";

    /**
     * TopicExchange(Topic 타입의 교환기 생성)
     */
    @Bean
    TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    /**
     * AMQP Queue 생성
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    /**
     *  RabbitTemplate이 메세지를 exchage할 때 일어나는 행위를 정의하도록 바인딩
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                  MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
