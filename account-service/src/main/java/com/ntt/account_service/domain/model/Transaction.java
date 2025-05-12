package com.ntt.account_service.domain.model;

import java.sql.Date;

import com.ntt.account_service.domain.enums.TransactionTypeEnum;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;   

    private Date date;            
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum transactionType;
    private Double value;
    private Long accountId;
}
