package com.ntt.account_service.domain.repository;

import com.ntt.account_service.domain.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findByAccountId(Long accountId);
    List<Transaction> findByAccountIdAndDateBetween(Long accountId, LocalDate startDate, LocalDate endDate);
}
