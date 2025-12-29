package com.oriontek.oriontek.customers.app.application.features.customers.command.UpdateCustomer;

import java.util.UUID;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Command;
import com.oriontek.oriontek.customers.app.application.dtos.customers.CustomerResponseDto;

public record UpdateCustomerCommand(
        UUID customerId,
        String name,
        String lastName,
        String email,
        String identificationNumber,
        int identificationType) implements Command<CustomerResponseDto> {
}
