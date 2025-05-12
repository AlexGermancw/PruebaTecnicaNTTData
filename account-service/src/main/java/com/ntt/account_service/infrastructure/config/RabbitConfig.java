package com.ntt.account_service.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public Queue customerResponseQueue() {
        return QueueBuilder.durable("customerResponseQueue").build();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("customerExchange");
    }
    @Bean
    public Queue queue() {
        return new Queue("customerQueue");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("customerRoutingKey");
    }

    @Bean
    public Queue customerQueue() {
        return new Queue("customerQueue");
    }
}
