package com.oriontek.oriontek.customers.app.application.features.customers.command.updateCustomerAddress;

import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.application.validation.Validator;

@Component
public class UpdateAddressCommandValidator implements Validator<UpdateAddressCommand> {

    @Override
    public Result<Void> validate(UpdateAddressCommand input) {

        if (input == null)
            return Result.failure(new Error("VALIDATION_ERROR", "Body cannnot be null"));

        if (input.customerId() == null)
            return Result.failure(new Error("VALIDATION_ERROR", "CustomerId is required  "));

        if (input.addressId() == null)
            return Result.failure(new Error("VALIDATION_ERROR", "AddressId is required"));

        if (isNullOrBlank(input.street()))
            return Result.failure(new Error("VALIDATION_ERROR", "Street is required"));

        if (input.street().length() < 3)
            return Result.failure(new Error("VALIDATION_ERROR", "Street must have at least 3 characters"));

        if (isNullOrBlank(input.city()))
            return Result.failure(new Error("VALIDATION_ERROR", "City is required"));

        if (isNullOrBlank(input.country()))
            return Result.failure(new Error("VALIDATION_ERROR", "Country is required"));

        return Result.success();
    }

    private boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
