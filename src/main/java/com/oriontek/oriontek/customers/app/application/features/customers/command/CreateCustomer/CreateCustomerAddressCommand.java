package com.oriontek.oriontek.customers.app.application.features.customers.command.CreateCustomer;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Command;

public record CreateCustomerAddressCommand(
        String street,
        String city,
        String country,
        boolean principal
) implements Command {}
