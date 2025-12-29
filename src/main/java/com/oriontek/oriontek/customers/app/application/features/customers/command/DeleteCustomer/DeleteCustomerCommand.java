package com.oriontek.oriontek.customers.app.application.features.customers.command.DeleteCustomer;

import java.util.UUID;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Command;

public record DeleteCustomerCommand(
        UUID customerId
) implements Command<Void> {}
