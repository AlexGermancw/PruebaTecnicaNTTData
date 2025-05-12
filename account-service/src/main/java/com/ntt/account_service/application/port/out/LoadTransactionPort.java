package com.ntt.account_service.application.port.out;

import com.ntt.account_service.domain.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoadTransactionPort {
    Transaction save(Transaction transaction);
    List<Transaction> findAll();
    Optional<Transaction> findById(Long id);
    void delete(Transaction transaction);
    List<Transaction> findByAccountIdAndDateBetween(Long accountId, LocalDate startDate, LocalDate endDate);
}
