package com.ntt.customer_service.adapter.rest.dto;

import com.ntt.customer_service.domain.enums.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private Long customerId;
    private String name;
    private CustomerStatusEnum status;
}
