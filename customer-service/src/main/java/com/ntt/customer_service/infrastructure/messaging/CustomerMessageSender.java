package com.ntt.customer_service.infrastructure.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class CustomerMessageSender {

    private final AmqpTemplate amqpTemplate;
    private final String queueName;

    public CustomerMessageSender(AmqpTemplate amqpTemplate,
                                  @Value("${customer.queue.name}") String queueName) {
        this.amqpTemplate = amqpTemplate;
        this.queueName = queueName;
    }

    public void sendCustomerId(Long customerId) {
        amqpTemplate.convertAndSend(queueName, customerId);
        System.out.println("Sent customer ID: " + customerId);
    }
}

