package com.ntt.account_service.application.port.in;

import com.ntt.account_service.adapters.rest.dto.*;

import java.util.List;

public interface TransactionUseCase {
    TransactionResponseDTO createTransaction(TransactionRequestDTO dto);
    List<TransactionResponseDTO> getAllTransactions();
    TransactionResponseDTO getTransactionById(Long id);
    void deleteTransaction(Long id);
}

