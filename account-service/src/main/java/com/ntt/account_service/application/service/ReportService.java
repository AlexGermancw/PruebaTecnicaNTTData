package com.ntt.account_service.application.service;

import com.ntt.account_service.adapters.rest.dto.*;
import com.ntt.account_service.application.port.in.ReportUseCase;
import com.ntt.account_service.application.port.out.LoadAccountPort;
import com.ntt.account_service.application.port.out.LoadTransactionPort;
import com.ntt.account_service.domain.model.*;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ReportService implements ReportUseCase {

    private final LoadAccountPort loadAccountPort;
    private final LoadTransactionPort loadTransactionPort;
    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate;
    private final Map<Long, CompletableFuture<CustomerDTO>> pendingRequests = new ConcurrentHashMap<>();

    private CustomerDTO customerResponseDTO;

    @Value("${customer-service.base-url}")
    private String baseUrl;

    @Value("${customer-service.get-customer-path}")
    private String customerPath;

     @Autowired
    public ReportService(LoadAccountPort loadAccountPort, 
                         LoadTransactionPort loadTransactionPort, 
                         RabbitTemplate rabbitTemplate, 
                         RestTemplate restTemplate) {
        this.loadAccountPort = loadAccountPort;
        this.loadTransactionPort = loadTransactionPort;
        this.rabbitTemplate = rabbitTemplate;
        this.restTemplate = restTemplate;
    }

    @Override
    public AccountStatementDTO getAccountStatement(Long customerId, LocalDate startDate, LocalDate endDate) {
        CompletableFuture<CustomerDTO> future = new CompletableFuture<>();
        pendingRequests.put(customerId, future);

        sendCustomerId(customerId);

        try {
            System.out.println("Esperando respuesta microservicio customer");
            customerResponseDTO = future.get(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: No se recibió respuesta del microservicio customer para el ID: " + customerId);
        } finally {
            pendingRequests.remove(customerId);
        }

        return buildAccountStatementDTO(customerId, startDate, endDate);
    }


    private void sendCustomerId(Long customerId) {
        rabbitTemplate.convertAndSend("customerExchange", "customerRoutingKey", customerId);
    }

    @RabbitListener(queues = "${customer.response.queue.name}")
    public void receiveCustomerResponse(CustomerDTO customer) {
        Long customerId = customer.getCustomerId();
        CompletableFuture<CustomerDTO> future = pendingRequests.get(customerId);

        if (future != null) {
            future.complete(customer);
        }
    }

    
    private AccountStatementDTO buildAccountStatementDTO(Long customerId, LocalDate startDate, LocalDate endDate) {
        if (customerResponseDTO == null) {
            throw new IllegalArgumentException("Error: Usuario no encontrado en el microservicio customer con ID: " + customerId);
        }

        List<Account> accounts = loadAccountPort.findByCustomerId(customerId);

        List<AccountReportDTO> accountReports = accounts.stream()
                .map(account -> buildAccountReport(account, startDate, endDate))
                .collect(Collectors.toList());

        return new AccountStatementDTO(
                customerId,
                customerResponseDTO.getName(),
                startDate,
                endDate,
                accountReports
        );
    }

    private AccountReportDTO buildAccountReport(Account account, LocalDate startDate, LocalDate endDate) {

        List<Transaction> transactions = loadTransactionPort.findByAccountIdAndDateBetween(
                account.getAccountId(), startDate, endDate
        );

        List<TransactionResponseDTO> transactionDTOs = transactions.stream()
                .map(tx -> new TransactionResponseDTO(tx.getTransactionId(), tx.getDate(), tx.getTransactionType(), tx.getValue()))
                .collect(Collectors.toList());

        return new AccountReportDTO(
                account.getAccountNumber(),
                account.getBalance(),
                transactionDTOs
        );
    }
}
