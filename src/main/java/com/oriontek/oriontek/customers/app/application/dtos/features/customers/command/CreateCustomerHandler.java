package com.oriontek.oriontek.customers.app.application.dtos.features.customers.command;


import org.springframework.stereotype.Component;
import com.oriontek.oriontek.customers.app.application.dtos.cqrs.CommandHandler;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.ICustomerRepository;

@Component("CreateCustomerCommandHandler")
public class CreateCustomerHandler implements CommandHandler<CreateCustomerCommand> {

    private final ICustomerRepository repository;

    public CreateCustomerHandler(ICustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(CreateCustomerCommand command) {

        Customer customer = Customer.create(command.name(), 
        command.lastName(), 
        command.email(), 
        command.identificationNumber(),
        command.identificationType());

        repository.save(customer);
    }
    
}
