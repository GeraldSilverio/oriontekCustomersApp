package com.oriontek.oriontek.customers.app.application.features.customers.command.UpdateCustomer;

import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.application.validation.Validator;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;

@Component
public class UpdateCustomerCommandValidator
        implements Validator<UpdateCustomerCommand> {

    private final CustomerRepository repository;

    public UpdateCustomerCommandValidator(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<Void> validate(UpdateCustomerCommand command) {

        if (command.customerId() == null) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "CustomerId is required"));
        }

        if (command.name() == null || command.name().isBlank()) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "Name is required"));
        }

        if (command.lastName() == null || command.lastName().isBlank()) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "Last name is required"));
        }

        if (command.email() == null || command.email().isBlank()) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "Email is required"));
        }

        if (!command.email().contains("@")) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "Invalid email format"));
        }

        var customer = repository.findById(command.customerId());

        if (!customer.get().getEmail().equals(command.email())) {
            if (repository.existsByEmail(
                    command.email())) {
                return Result.failure(
                        new Error("BUSINESS_ERROR", "Email already exists"));
            }
        }
        if (!customer.get().getIdentificationNumber().equals(command.identificationNumber())) {

            if (repository.existsByIdentificationNumber(
                    command.identificationNumber())) {
                return Result.failure(
                        new Error("BUSINESS_ERROR", "Identification already exists"));
            }
        }

        return Result.success();
    }
}
