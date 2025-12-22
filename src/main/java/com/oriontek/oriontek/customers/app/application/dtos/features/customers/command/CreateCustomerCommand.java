package com.oriontek.oriontek.customers.app.application.dtos.features.customers.command;

import com.oriontek.oriontek.customers.app.application.dtos.cqrs.Command;

public record CreateCustomerCommand(
        String name,
        String lastName,
        String email,
        String identificationNumber,
        int identificationType
) implements Command {}
