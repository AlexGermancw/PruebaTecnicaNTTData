package com.ntt.customer_service;

import com.ntt.customer_service.adapter.rest.dto.*;
import com.ntt.customer_service.domain.enums.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateCustomerSuccessfully() {
        // Arrange
        CustomerRequestDTO request = new CustomerRequestDTO();
        request.setName("Alex");
        request.setGender("M");
        request.setAge(30);
        request.setIdentification("1234567890");
        request.setAddress("Av. Siempre Viva 123");
        request.setPhone("0999999999");
        request.setPassword("secret");
        request.setStatus(CustomerStatusEnum.ACTIVE);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CustomerRequestDTO> requestEntity = new HttpEntity<>(request, headers);

        // Act
        ResponseEntity<CustomerResponseDTO> response = restTemplate.postForEntity("/customers", requestEntity, CustomerResponseDTO.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getName());
        assertEquals(CustomerStatusEnum.ACTIVE, response.getBody().getStatus());
    }
}
