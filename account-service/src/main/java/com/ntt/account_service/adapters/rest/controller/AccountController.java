package com.ntt.account_service.adapters.rest.controller;

import com.ntt.account_service.application.port.in.AccountUseCase;
import com.ntt.account_service.adapters.rest.dto.AccountRequestDTO;
import com.ntt.account_service.adapters.rest.dto.AccountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class AccountController {

    private final AccountUseCase accountUseCase;

    @PostMapping
    public AccountResponseDTO create(@RequestBody AccountRequestDTO accountRequestDTO) {
        System.out.println(">>> controller");
        return accountUseCase.createAccount(accountRequestDTO);
    }

    @GetMapping
    public List<AccountResponseDTO> getAll() {
        return accountUseCase.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getById(@PathVariable Long id) {
        return accountUseCase.getAccountById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountUseCase.deleteAccount(id);
    }
}


