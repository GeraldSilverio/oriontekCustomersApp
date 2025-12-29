package com.oriontek.oriontek.customers.app.presentation.controller;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oriontek.oriontek.customers.app.application.cqrs.bus.CommandBus;
import com.oriontek.oriontek.customers.app.application.cqrs.bus.QueryBus;
import com.oriontek.oriontek.customers.app.application.dtos.address.AddAddressRequestDto;
import com.oriontek.oriontek.customers.app.application.dtos.address.UpdateAddressRequestDto;
import com.oriontek.oriontek.customers.app.application.dtos.customers.CustomerResponseDto;
import com.oriontek.oriontek.customers.app.application.dtos.customers.UpdateCustomerRequestDto;
import com.oriontek.oriontek.customers.app.application.features.customers.command.AddAddressToCustomer.AddAddressToCustomerCommand;
import com.oriontek.oriontek.customers.app.application.features.customers.command.CreateCustomer.CreateCustomerCommand;
import com.oriontek.oriontek.customers.app.application.features.customers.command.DeleteAddress.DeleteAddressCommand;
import com.oriontek.oriontek.customers.app.application.features.customers.command.DeleteCustomer.DeleteCustomerCommand;
import com.oriontek.oriontek.customers.app.application.features.customers.command.UpdateCustomer.UpdateCustomerCommand;
import com.oriontek.oriontek.customers.app.application.features.customers.command.updateCustomerAddress.UpdateAddressCommand;
import com.oriontek.oriontek.customers.app.application.features.customers.query.GetAllCustomer.GetAllCustomersQuery;
import com.oriontek.oriontek.customers.app.application.features.customers.query.GetCustomerById.GetCustomerByIdQuery;
import com.oriontek.oriontek.customers.app.application.result.Result;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public CustomerController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping
    public ResponseEntity<Result> create(
            @RequestBody CreateCustomerCommand command) {
        Result<Void> result = commandBus.send(command);

        if (result.isFailure()) {
            return ResponseEntity
                    .badRequest()
                    .body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{customerId}/addresses")
    public ResponseEntity<Result> addAddress(
            @PathVariable UUID customerId,
            @RequestBody AddAddressRequestDto request) {

        Result result = commandBus.send(
                new AddAddressToCustomerCommand(
                        customerId,
                        request.street(),
                        request.city(),
                        request.country(),
                        request.principal()));

        if (result.isFailure()) {
            return ResponseEntity
                    .badRequest()
                    .body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<Result<Page<CustomerResponseDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Result<Page<CustomerResponseDto>> result = queryBus.ask(new GetAllCustomersQuery(page, size));

        if (result.isFailure()) {
            if ("NOT_FOUND".equals(result.getError().code())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<CustomerResponseDto>> getById(@PathVariable UUID id) {

        Result<CustomerResponseDto> result = queryBus.ask(new GetCustomerByIdQuery(id));

        if (result.isFailure()) {
            if ("NOT_FOUND".equals(result.getError().code())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(
            @PathVariable UUID id,
            @RequestBody UpdateCustomerRequestDto request) {

        UpdateCustomerCommand command = new UpdateCustomerCommand(
                id,
                request.name(),
                request.lastName(),
                request.email(),
                request.identificationNumber(),
                request.identificationType());

        Result<CustomerResponseDto> result = commandBus.send(command);

        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<Result<Void>> updateAddress(
            @PathVariable UUID customerId,
            @PathVariable UUID addressId,
            @RequestBody UpdateAddressRequestDto request) {

        UpdateAddressCommand command = new UpdateAddressCommand(
                customerId,
                addressId,
                request.street(),
                request.city(),
                request.country(),
                request.principal());

        Result<Void> result = commandBus.send(command);

        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Result<Void>> deleteCustomer(
            @PathVariable UUID customerId) {

        Result<Void> result = commandBus.send(new DeleteCustomerCommand(customerId));

        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<Result<Void>> deleteAddress(
            @PathVariable UUID customerId,
            @PathVariable UUID addressId) {

        Result<Void> result = commandBus.send(new DeleteAddressCommand(customerId, addressId));

        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

}
