package com.ntt.customer_service.infrastructure.repository;

import com.ntt.customer_service.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerRepository extends JpaRepository<Customer, Long> {

}
