package com.ntt.account_service.adapters.rest.controller;

import com.ntt.account_service.application.port.in.TransactionUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ntt.account_service.adapters.rest.dto.TransactionRequestDTO;
import com.ntt.account_service.adapters.rest.dto.TransactionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@Tag(name = "Movimientos", description = "API para gestionar transacciones (DEPOSITO, RETIRO) de cuentas")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    @Operation(
        summary = "Crear transacción",
        description = "Registra una nueva transacción (depósito o retiro) en una cuenta existente"
    )
    @PostMapping
    public TransactionResponseDTO create(
        @RequestBody TransactionRequestDTO transactionRequestDTO) {
        return transactionUseCase.createTransaction(transactionRequestDTO);
    }

    @Operation(
        summary = "Listar todas las transacciones",
        description = "Retorna una lista con todas las transacciones registradas"
    )
    @GetMapping
    public List<TransactionResponseDTO> getAll() {
        return transactionUseCase.getAllTransactions();
    }

    @Operation(
        summary = "Obtener transacción por ID",
        description = "Busca una transacción específica mediante su ID"
    )
    @GetMapping("/{id}")
    public TransactionResponseDTO getById(
        @PathVariable Long id) {
        return transactionUseCase.getTransactionById(id);
    }

    @Operation(
        summary = "Eliminar transacción",
        description = "Elimina una transacción del sistema según su ID"
    )
    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable Long id) {
        transactionUseCase.deleteTransaction(id);
    }
}
