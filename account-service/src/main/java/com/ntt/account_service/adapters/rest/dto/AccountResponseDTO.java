package com.ntt.account_service.adapters.rest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {

    private Long accountId;
    private String accountNumber;
    private String status;
    private Long customerId;
}
