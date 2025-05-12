package com.ntt.account_service.application.port.in;

import com.ntt.account_service.adapters.rest.dto.*;

import java.util.List;

public interface AccountUseCase {
    AccountResponseDTO createAccount(AccountRequestDTO dto);
    List<AccountResponseDTO> getAllAccounts();
    AccountResponseDTO getAccountById(Long id);
    void deleteAccount(Long id);
}

