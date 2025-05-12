package com.ntt.account_service.adapters.rest.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatementDTO {
    private Long customerId;
    private String CustomerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<AccountReportDTO> accounts;
}
