package com.ntt.account_service.adapters.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.ntt.account_service.domain.enums.TransactionTypeEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {

    private LocalDate date;
    private TransactionTypeEnum transactionType;
    private Double value;
    private Long accountId;
}
