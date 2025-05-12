package com.ntt.customer_service.application.service;


import org.springframework.stereotype.Service;

import com.ntt.customer_service.adapter.rest.dto.*;
import com.ntt.customer_service.application.port.in.CustomerUseCase;
import com.ntt.customer_service.application.port.out.CustomerRepository;
import com.ntt.customer_service.domain.model.Customer;
import com.ntt.customer_service.infrastructure.messaging.CustomerMessageSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.stream.Collectors;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerMessageSender customerMessageSender;
    private final RabbitTemplate rabbitTemplate;
    
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;
    
    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setPassword(dto.getPassword());
        customer.setAge(dto.getAge());
        customer.setGender(dto.getGender());
        customer.setIdentification(dto.getIdentification());
        customer.setPhone(dto.getPhone());
        customer.setStatus(dto.getStatus());
        Customer saved = customerRepository.save(customer);

        customerMessageSender.sendCustomerId(saved.getCustomerId());

        return new CustomerResponseDTO(saved.getCustomerId(), saved.getName(), saved.getStatus());
    }
    
    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(c -> new CustomerResponseDTO(c.getCustomerId(), c.getName(), c.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));

        return new CustomerResponseDTO(customer.getCustomerId(), customer.getName(), customer.getStatus());
    }


    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
