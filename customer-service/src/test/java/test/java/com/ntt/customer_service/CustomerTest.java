package test.java.com.ntt.customer_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ntt.customer_service.domain.enums.CustomerStatusEnum;
import com.ntt.customer_service.domain.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(1L, "password123", CustomerStatusEnum.ACTIVE);
    }

    @Test
    void testCustomerCreation() {
        assertNotNull(customer);
        assertEquals(1L, customer.getCustomerId());
        assertEquals("password123", customer.getPassword());
        assertEquals(CustomerStatusEnum.ACTIVE, customer.getStatus());
    }

    @Test
    void testCustomerStatus() {
        assertEquals(CustomerStatusEnum.ACTIVE, customer.getStatus());
    }

    @Test
    void testCustomerPassword() {
        assertEquals("password123", customer.getPassword());
    }
}