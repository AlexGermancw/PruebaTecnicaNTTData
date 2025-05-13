package com.ntt.customer_service.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {
    private String name;
    private String gender;
    private Integer age;
    @Column(unique = true, nullable = false)
    private String identification;
    private String address;
    private String phone;
}
