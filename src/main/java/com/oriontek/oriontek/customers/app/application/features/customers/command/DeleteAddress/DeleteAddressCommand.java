package com.oriontek.oriontek.customers.app.application.features.customers.command.DeleteAddress;

import java.util.UUID;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Command;

public record DeleteAddressCommand(
        UUID customerId,
        UUID addressId
) implements Command<Void> {}
