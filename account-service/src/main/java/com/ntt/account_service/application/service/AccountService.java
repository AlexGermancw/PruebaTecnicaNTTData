package com.ntt.account_service.application.service;

import com.ntt.account_service.domain.model.Account;


import com.ntt.account_service.application.port.in.AccountUseCase;
import com.ntt.account_service.application.port.out.LoadAccountPort;
import com.ntt.account_service.adapters.rest.dto.AccountRequestDTO;
import com.ntt.account_service.adapters.rest.dto.AccountResponseDTO;
import lombok.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;


import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AccountService implements AccountUseCase {

    private final LoadAccountPort loadAccountPort;
    private final RestTemplate restTemplate;

    @Value("${customer-service.base-url}")
    private String customerServiceBaseUrl;

    @Value("${customer-service.get-customer-path}")
    private String customerServiceGetCustomerPath;

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO dto) {

        if (!isCustomerExists(dto.getCustomerId())) {
            throw new IllegalArgumentException("Error al validar cliente en customer_service con customerID: " + dto.getCustomerId());
        }

        Optional<Account> existingAccount = loadAccountPort.findByAccountNumber(dto.getAccountNumber());

        if (existingAccount.isPresent()) {
            throw new IllegalArgumentException("El número de cuenta ya existe.");
        }

        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setAccountType(dto.getAccountType());
        account.setBalance(dto.getBalance());
        account.setStatus(dto.getStatus());
        account.setCustomerId(dto.getCustomerId());

        Account savedAccount = loadAccountPort.save(account);
        return new AccountResponseDTO(savedAccount.getAccountId(), savedAccount.getAccountNumber(), savedAccount.getStatus(),savedAccount.getCustomerId());
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        return loadAccountPort.findAll()
                .stream()
                .map(account -> new AccountResponseDTO(
                        account.getAccountId(),
                        account.getAccountNumber(),
                        account.getStatus(),
                        account.getCustomerId()))
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {
        Account account = loadAccountPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        return new AccountResponseDTO(
                account.getAccountId(),
                account.getAccountNumber(),
                account.getStatus(),
                account.getCustomerId()
        );
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = loadAccountPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        loadAccountPort.delete(account.getAccountId());
    }

    private boolean isCustomerExists(Long customerId) {
        try {
            String url = customerServiceBaseUrl + customerServiceGetCustomerPath + customerId;
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
}
