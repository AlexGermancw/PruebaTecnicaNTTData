package com.ntt.account_service.adapters.rest.controller;

import com.ntt.account_service.application.port.in.TransactionUseCase;
import com.ntt.account_service.adapters.rest.dto.TransactionRequestDTO;
import com.ntt.account_service.adapters.rest.dto.TransactionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    @PostMapping
    public TransactionResponseDTO create(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return transactionUseCase.createTransaction(transactionRequestDTO);
    }

    @GetMapping
    public List<TransactionResponseDTO> getAll() {
        return transactionUseCase.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO getById(@PathVariable Long id) {
        return transactionUseCase.getTransactionById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transactionUseCase.deleteTransaction(id);
    }
}
