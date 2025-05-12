package com.ntt.account_service.adapters.rest.dto;

import com.ntt.account_service.domain.enums.AccountTypeEnum;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {

    private String accountNumber;
    private AccountTypeEnum accountType;
    private Double balance;
    private String status;
    private Long customerId;
}
