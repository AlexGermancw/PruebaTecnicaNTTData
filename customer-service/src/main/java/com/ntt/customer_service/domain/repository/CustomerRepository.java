package com.ntt.customer_service.domain.repository;

import com.ntt.customer_service.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    void deleteById(Long id);
}
