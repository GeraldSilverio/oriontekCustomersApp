package com.oriontek.oriontek.customers.app.application.features.customers.command.CreateCustomer;

import java.util.List;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Command;

public record CreateCustomerCommand(
        String name,
        String lastName,
        String email,
        String identificationNumber,
        int identificationType,
        List<CreateCustomerAddressCommand> addresses
) implements Command<Void> {}
