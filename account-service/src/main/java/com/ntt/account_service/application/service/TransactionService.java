package com.ntt.account_service.application.service;

import com.ntt.account_service.domain.enums.TransactionTypeEnum;
import com.ntt.account_service.domain.model.Account;
import com.ntt.account_service.domain.model.Transaction;
import com.ntt.account_service.application.port.in.TransactionUseCase;
import com.ntt.account_service.application.port.out.LoadAccountPort;
import com.ntt.account_service.application.port.out.LoadTransactionPort;
import com.ntt.account_service.adapters.rest.dto.TransactionRequestDTO;
import com.ntt.account_service.adapters.rest.dto.TransactionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {

    private final LoadTransactionPort loadTransactionPort;
    private final LoadAccountPort loadAccountPort;

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO dto) {
        
        Account account = loadAccountPort.findById(dto.getAccountId())
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        Double currentBalance = account.getBalance();
        Double transactionValue = dto.getValue();

        TransactionTypeEnum type;
        if (transactionValue < 0) {
            type = TransactionTypeEnum.RETIRO;
            if (currentBalance + transactionValue < 0) {
                throw new IllegalArgumentException("Saldo insuficiente");
            }
        } else {
            type = TransactionTypeEnum.DEPOSITO;
        }

        Double newBalance = currentBalance + transactionValue;
        account.setBalance(newBalance);
        loadAccountPort.save(account);

        Transaction transaction = new Transaction();
        transaction.setDate(Date.valueOf(dto.getDate()));
        transaction.setTransactionType(type);
        transaction.setValue(Math.abs(transactionValue));
        transaction.setAccountId(dto.getAccountId());

        Transaction savedTransaction = loadTransactionPort.save(transaction);

        return new TransactionResponseDTO(
            savedTransaction.getTransactionId(),
            savedTransaction.getDate(),
            savedTransaction.getTransactionType(),
            savedTransaction.getValue()
        );
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        return loadTransactionPort.findAll()
                .stream()
                .map(transaction -> new TransactionResponseDTO(transaction.getTransactionId(), transaction.getDate(), transaction.getTransactionType(), transaction.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long id) {
        Transaction transaction = loadTransactionPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimiento no encontrado"));

        return new TransactionResponseDTO(transaction.getTransactionId(), transaction.getDate(), transaction.getTransactionType(), transaction.getValue());
    }

    @Override
    public void deleteTransaction(Long id) {
        Transaction transaction = loadTransactionPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimiento no encontrado"));
        loadTransactionPort.delete(transaction);
    }
}
