package com.ntt.account_service.infrastructure.repository;

import com.ntt.account_service.domain.model.Account;

import com.ntt.account_service.application.port.out.LoadAccountPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class JpaAccountRepository implements LoadAccountPort {

    private final SpringDataAccountRepository springDataAccountRepository;

    public JpaAccountRepository(SpringDataAccountRepository springDataAccountRepository) {
        this.springDataAccountRepository = springDataAccountRepository;
    }

    @Override
    public Account save(Account account) {
        return springDataAccountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return springDataAccountRepository.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return springDataAccountRepository.findAll();
    }

    @Override
    public void delete(Long accountId) {
        springDataAccountRepository.deleteById(accountId);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return springDataAccountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public List<Account> findByCustomerId(Long customerId) {
        return springDataAccountRepository.findByCustomerId(customerId);
    }
}

