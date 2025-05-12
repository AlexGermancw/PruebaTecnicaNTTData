package com.ntt.customer_service.adapter.rest.controller;
import com.ntt.customer_service.adapter.rest.dto.*;
import com.ntt.customer_service.application.port.in.CustomerUseCase;

import lombok.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    @PostMapping
    public CustomerResponseDTO create(@RequestBody CustomerRequestDTO dto) {
        return customerUseCase.createCustomer(dto);
    }

    @GetMapping
    public List<CustomerResponseDTO> getAll() {
        return customerUseCase.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getById(@PathVariable Long id) {
        System.out.println(">>> controller ");
        return customerUseCase.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerUseCase.deleteCustomer(id);
    }
}
