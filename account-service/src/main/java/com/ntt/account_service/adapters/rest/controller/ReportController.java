package com.ntt.account_service.adapters.rest.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.ntt.account_service.adapters.rest.dto.AccountStatementDTO;
import com.ntt.account_service.application.service.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    @Operation(
        summary = "Obtener reporte de estado de cuenta",
        description = "Retorna el estado de cuenta de un cliente dado su ID y un rango de fechas. "
                    + "El formato de fecha debe ser 'aaaa-mm-dd' (ISO 8601)."
    )
    public AccountStatementDTO getAccountStatement(
        @RequestParam Long customerId,
        @Parameter(description = "Formato (aaaa-mm-dd)")
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @Parameter(description = "Formato (aaaa-mm-dd)")
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return reportService.getAccountStatement(customerId, startDate, endDate);
    }
}
