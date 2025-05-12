package com.ntt.account_service.domain.repository;

import com.ntt.account_service.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(Long id);
    List<Account> findAll();
    void deleteById(Long id);
}