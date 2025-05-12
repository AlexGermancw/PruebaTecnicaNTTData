package com.ntt.account_service.infrastructure.repository;

import com.ntt.account_service.domain.model.Transaction;
import com.ntt.account_service.application.port.out.LoadTransactionPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaTransactionRepository implements LoadTransactionPort {

    private final SpringDataTransactionRepository springDataTransactionRepository;

    public JpaTransactionRepository(SpringDataTransactionRepository springDataTransactionRepository) {
        this.springDataTransactionRepository = springDataTransactionRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return springDataTransactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return springDataTransactionRepository.findById(id);
    }

    @Override
    public List<Transaction> findAll() {
        return (List<Transaction>) springDataTransactionRepository.findAll();
    }

    @Override
    public void delete(Transaction transaction) {
        springDataTransactionRepository.delete(transaction);
    }

    @Override
    public List<Transaction> findByAccountIdAndDateBetween(Long accountId, LocalDate startDate, LocalDate endDate) {
        return springDataTransactionRepository.findByAccountIdAndDateBetween(accountId, startDate, endDate);
    }
}
