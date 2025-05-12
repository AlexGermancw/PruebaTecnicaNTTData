package com.ntt.account_service.application.port.in;

import com.ntt.account_service.adapters.rest.dto.*;

import java.time.LocalDate;

public interface ReportUseCase {

    AccountStatementDTO getAccountStatement(Long customerId, LocalDate startDate, LocalDate endDate);
}
