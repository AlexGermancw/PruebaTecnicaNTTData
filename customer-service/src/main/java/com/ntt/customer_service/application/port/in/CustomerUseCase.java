package com.ntt.customer_service.application.port.in;

import java.util.List;

import com.ntt.customer_service.adapter.rest.dto.*;

public interface CustomerUseCase {
    CustomerResponseDTO createCustomer(CustomerRequestDTO dto);
    List<CustomerResponseDTO> getAllCustomers();
    CustomerResponseDTO getCustomerById(Long id);
    void deleteCustomer(Long id);
}
