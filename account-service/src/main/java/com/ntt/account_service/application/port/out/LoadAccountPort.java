package com.ntt.account_service.application.port.out;

import com.ntt.account_service.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface LoadAccountPort {
    Account save(Account account);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findAll();
    Optional<Account> findById(Long id);
    void delete(Long accountId);
    List<Account> findByCustomerId(Long customerId);
}

