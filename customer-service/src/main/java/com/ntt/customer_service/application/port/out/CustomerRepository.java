package com.ntt.customer_service.application.port.out;

import java.util.List;
import java.util.Optional;

import com.ntt.customer_service.domain.model.Customer;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    void deleteById(Long id);    
    boolean existsByIdentification(String identification);
}
