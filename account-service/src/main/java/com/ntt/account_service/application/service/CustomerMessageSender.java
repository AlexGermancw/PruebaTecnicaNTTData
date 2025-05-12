package com.ntt.account_service.application.service;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.*;

@Service
@RequiredArgsConstructor
public class CustomerMessageSender {

    private final AmqpTemplate amqpTemplate;

    @Value("${customer.queue.name}")
    private String queueName;

    public void sendCustomerId(Long customerId) {
        amqpTemplate.convertAndSend(queueName, customerId);
        System.out.println("Sent customer ID: " + customerId);
    }
}

