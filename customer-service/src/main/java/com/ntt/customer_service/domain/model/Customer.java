package com.ntt.customer_service.domain.model;

import com.ntt.customer_service.domain.enums.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String password;

    @Enumerated(EnumType.STRING)
    private CustomerStatusEnum status;
}


