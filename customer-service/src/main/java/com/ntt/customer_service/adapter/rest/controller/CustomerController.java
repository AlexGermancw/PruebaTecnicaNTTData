package com.ntt.customer_service.adapter.rest.controller;
import com.ntt.customer_service.adapter.rest.dto.*;
import com.ntt.customer_service.application.port.in.CustomerUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Clientes", description = "API - miroservicio clientes")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    @PostMapping
    @Operation(summary = "Crear Cliente", description = "Crea un nuevo cliente, valida que no se repita identificacion y retorna el detalle")
    public CustomerResponseDTO create(@RequestBody CustomerRequestDTO dto) {
        return customerUseCase.createCustomer(dto);
    }

    @GetMapping
    @Operation(summary = "Lita todos los clientes", description = "Retorna una lista de clientes")
    public List<CustomerResponseDTO> getAll() {
        return customerUseCase.getAllCustomers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obetnet Cliente por ID", description = "retorna el cliente por ID")
    public CustomerResponseDTO getById(@PathVariable Long id) {
        return customerUseCase.getCustomerById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Cliente", description = "Actualiza el cliente por ID")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id, @RequestBody CustomerRequestDTO dto) {
        return ResponseEntity.ok(customerUseCase.updateCustomer(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente por ID")
    public void delete(@PathVariable Long id) {
        customerUseCase.deleteCustomerById(id);
    }
}
