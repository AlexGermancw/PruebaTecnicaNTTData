package com.ntt.account_service.adapters.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

import com.ntt.account_service.domain.enums.TransactionTypeEnum;


@Data
@AllArgsConstructor
public class TransactionResponseDTO {

    private Long transactionId;
    private Date date;
    private TransactionTypeEnum transactionType;
    private Double value;
}
