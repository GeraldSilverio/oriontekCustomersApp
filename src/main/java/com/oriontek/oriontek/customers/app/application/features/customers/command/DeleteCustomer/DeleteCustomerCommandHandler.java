package com.oriontek.oriontek.customers.app.application.features.customers.command.DeleteCustomer;

import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.CommandHandler;
import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;

@Component("DeleteCustomerCommandHandler")
public class DeleteCustomerCommandHandler
        implements CommandHandler<DeleteCustomerCommand, Void> {

    private final CustomerRepository repository;

    public DeleteCustomerCommandHandler(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<Void> handle(DeleteCustomerCommand command) {

        Customer customer = repository.findById(command.customerId())
                .orElse(null);

        if (customer == null) {
            return Result.failure(
                    new Error("NOT_FOUND", "Customer not found"));
        }

        customer.softDelete("SYSTEM");

        repository.save(customer);

        return Result.success();
    }
}
