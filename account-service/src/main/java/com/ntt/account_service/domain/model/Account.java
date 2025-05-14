package com.ntt.account_service.domain.model;

import com.ntt.account_service.domain.enums.AccountTypeEnum;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;      

    private String accountNumber; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountTypeEnum accountType; 
    private Double balance;       
    private String status;

    private Long customerId;
}

