package com.ntt.account_service.adapters.rest.controller;

import com.ntt.account_service.application.port.in.AccountUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ntt.account_service.adapters.rest.dto.AccountRequestDTO;
import com.ntt.account_service.adapters.rest.dto.AccountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@Tag(name = "Cuentas", description = "API - microservicio de cuentas bancarias")
@RequiredArgsConstructor
public class AccountController {

    private final AccountUseCase accountUseCase;

    @PostMapping
    @Operation(summary = "Crear cuenta", description = "Crea una nueva cuenta bancaria asociada a un cliente")
    public AccountResponseDTO create(@RequestBody AccountRequestDTO accountRequestDTO) {
        return accountUseCase.createAccount(accountRequestDTO);
    }

    @GetMapping
    @Operation(summary = "Listar cuentas", description = "Obtiene la lista completa de cuentas registradas")
    public List<AccountResponseDTO> getAll() {
        return accountUseCase.getAllAccounts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cuenta por ID", description = "Obtiene el detalle de una cuenta específica por su ID")
    public AccountResponseDTO getById(@PathVariable Long id) {
        return accountUseCase.getAccountById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cuenta", description = "Elimina una cuenta específica por su ID")
    public void delete(@PathVariable Long id) {
        accountUseCase.deleteAccount(id);
    }
}
