package com.ntt.account_service.adapters.rest.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.ntt.account_service.adapters.rest.dto.AccountStatementDTO;
import com.ntt.account_service.application.service.ReportService;

import lombok.*;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public AccountStatementDTO getAccountStatement(
        @RequestParam Long customerId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        System.out.println(">>> controller");
        return reportService.getAccountStatement(customerId, startDate, endDate);
    }
}
