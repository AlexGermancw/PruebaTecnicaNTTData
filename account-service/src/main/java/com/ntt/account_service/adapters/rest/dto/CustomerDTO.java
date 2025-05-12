package com.ntt.account_service.adapters.rest.dto;

import com.ntt.account_service.domain.enums.CustomerStatusEnum;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long customerId;
    private String name;
    private CustomerStatusEnum status;
}