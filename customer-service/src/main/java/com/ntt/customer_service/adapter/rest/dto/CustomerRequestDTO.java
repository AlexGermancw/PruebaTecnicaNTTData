package com.ntt.customer_service.adapter.rest.dto;

import java.io.Serializable;

import com.ntt.customer_service.domain.enums.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO implements Serializable {
    private long customerId;
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private CustomerStatusEnum status;
}