package com.oriontek.oriontek.customers.app.application.features.customers.command.updateCustomerAddress;

import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.CommandHandler;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.domain.Models.Address;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.AddressRepository;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;
import com.oriontek.oriontek.customers.app.application.result.Error;
@Component("UpdateAddressCommandHandler")
public class UpdateAddressHandler
        implements CommandHandler<UpdateAddressCommand, Void> {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final UpdateAddressCommandValidator validator;

    public UpdateAddressHandler(
            CustomerRepository customerRepository,
            AddressRepository addressRepository,
            UpdateAddressCommandValidator validator) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.validator = validator;
    }

    @Override
    public Result<Void> handle(UpdateAddressCommand command) {

        Result validations = validator.validate(command);

        if (validations.isFailure())
            return validations;

        Customer customer = customerRepository.findById(command.customerId())
                .orElse(null);

        if (customer == null) {
            return Result.failure(new Error("NOT_FOUND", "Customer not found"));
        }

        Address address = customer.getAddresses().stream()
                .filter(a -> a.getIdAddress().equals(command.addressId()))
                .findFirst()
                .orElse(null);

        if (address == null) {
            return Result.failure(new Error("NOT_FOUND", "Address not found"));
        }

        if (command.principal()) {
            customer.getAddresses()
                    .forEach(a -> a.markAsPrincipal());
        }

        address.update(
                command.street(),
                command.city(),
                command.country(),
                command.principal(),
                "SYSTEM");

        addressRepository.save(address);

        return Result.success();
    }
}
