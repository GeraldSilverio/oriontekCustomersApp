package com.oriontek.oriontek.customers.app.application.features.customers.command.AddAddressToCustomer;

import java.util.UUID;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Command;


public record AddAddressToCustomerCommand(
        UUID customerId,
        String street,
        String city,
        String country,
        boolean principal
) implements Command<Void> {}