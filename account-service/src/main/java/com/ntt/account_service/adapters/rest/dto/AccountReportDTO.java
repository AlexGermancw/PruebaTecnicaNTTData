package com.ntt.account_service.adapters.rest.dto;

import java.util.List;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountReportDTO {
    private String accountNumber;
    private Double balance;
    private List<TransactionResponseDTO> transactions;
}

