package com.oriontek.oriontek.customers.app.application.features.customers.command.CreateCustomer;

import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.application.validation.Validator;

@Component
public class CreateCustomerCommandValidator
        implements Validator<CreateCustomerCommand> {

    @Override
    public Result<Void> validate(CreateCustomerCommand command) {

        if (command == null) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "Command cannot be null"));
        }

        if (command.name() == null || command.name().isBlank()) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "First name is required"));
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

        if (command.identificationNumber() == null
                || command.identificationNumber().isBlank()) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "Identification number is required"));
        }

        if (command.addresses() == null || command.addresses().isEmpty()) {
            return Result.failure(
                    new Error("VALIDATION_ERROR", "At least one address is required"));
        }

        boolean hasPrincipalAddress = false;

        for (CreateCustomerAddressCommand address : command.addresses()) {

            if (address.street() == null || address.street().isBlank()) {
                return Result.failure(
                        new Error("VALIDATION_ERROR", "Street is required"));
            }

            if (address.city() == null || address.city().isBlank()) {
                return Result.failure(
                        new Error("VALIDATION_ERROR", "City is required"));
            }

            if (address.country() == null || address.country().isBlank()) {
                return Result.failure(
                        new Error("VALIDATION_ERROR", "Country is required"));
            }

            if (address.principal()) {
                if (hasPrincipalAddress) {
                    return Result.failure(
                            new Error(
                                    "VALIDATION_ERROR",
                                    "Only one principal address is allowed"));
                }
                hasPrincipalAddress = true;
            }
        }

        return Result.success();
    }
}
