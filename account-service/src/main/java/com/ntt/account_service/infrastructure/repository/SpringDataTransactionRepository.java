package com.ntt.account_service.infrastructure.repository;

import com.ntt.account_service.domain.model.Transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountIdAndDateBetween(Long accountId, LocalDate startDate, LocalDate endDate);
}
