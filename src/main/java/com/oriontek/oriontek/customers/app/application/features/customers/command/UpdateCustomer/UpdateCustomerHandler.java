package com.oriontek.oriontek.customers.app.application.features.customers.command.UpdateCustomer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.CommandHandler;
import com.oriontek.oriontek.customers.app.application.dtos.customers.CustomerResponseDto;
import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.mapper.CustomerMapper;

@Component("UpdateCustomerCommandHandler")
public class UpdateCustomerHandler
        implements CommandHandler<UpdateCustomerCommand,CustomerResponseDto> {

    private final CustomerRepository repository;
    private final UpdateCustomerCommandValidator validator;

    public UpdateCustomerHandler(
            CustomerRepository repository,
            UpdateCustomerCommandValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Result<CustomerResponseDto> handle(UpdateCustomerCommand command) {

        Result<Void> validation = validator.validate(command);
        if (validation.isFailure()) {
            return Result.failure(validation.getError());
        }

        Customer customer = repository.findById(command.customerId())
                .orElse(null);

        if (customer == null) {
            return Result.failure(
                    new Error("NOT_FOUND", "Customer not found"));
        }

        customer.update(
                command.name(),
                command.lastName(),
                command.email(),
                command.identificationNumber(),
                command.identificationType(),
                "SYSTEM",
                LocalDateTime.now());

        repository.save(customer);

        return Result.success(CustomerMapper.toDto(customer));
    }
}
