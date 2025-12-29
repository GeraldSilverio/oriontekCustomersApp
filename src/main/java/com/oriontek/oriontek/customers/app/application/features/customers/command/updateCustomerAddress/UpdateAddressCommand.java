package com.oriontek.oriontek.customers.app.application.features.customers.command.updateCustomerAddress;

import java.util.UUID;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Command;

public record UpdateAddressCommand(
        UUID customerId,
        UUID addressId,
        String street,
        String city,
        String country,
        boolean principal
) implements Command<Void> {}

