package com.oriontek.oriontek.customers.app.application.features.customers.command.AddAddressToCustomer;

import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.application.validation.Validator;

@Component
public class AddAddressToCustomerCommandValidator
        implements Validator<AddAddressToCustomerCommand> {

    @Override
    public Result<Void> validate(AddAddressToCustomerCommand command) {

        if (command == null) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "Command cannot be null"));
        }

        if (command.customerId() == null) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "CustomerId is required"));
        }

        if (command.street() == null || command.street().isBlank()) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "Street is required"));
        }

        if (command.city() == null || command.city().isBlank()) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "City is required"));
        }

        if (command.country() == null || command.country().isBlank()) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "Country is required"));
        }

        return Result.success();
    }
}
