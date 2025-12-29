package com.oriontek.oriontek.customers.app.application.features.customers.command.DeleteAddress;

import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.CommandHandler;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.domain.Models.Address;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.AddressRepository;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;

@Component("DeleteAddressCommandHandler")
public class DeleteAddressCommandHandler
        implements CommandHandler<DeleteAddressCommand, Void> {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public DeleteAddressCommandHandler(
            CustomerRepository customerRepository,
            AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Result<Void> handle(DeleteAddressCommand command) {

        Customer customer = customerRepository.findById(command.customerId())
                .orElse(null);

        if (customer == null) {
            return Result.failure(
                    new Error("NOT_FOUND", "Customer not found"));
        }

        Address address = customer.getAddresses().stream()
                .filter(a -> a.getIdAddress().equals(command.addressId()))
                .findFirst()
                .orElse(null);

        if (address == null) {
            return Result.failure(
                    new Error("NOT_FOUND", "Address not found"));
        }

        address.softDelete("SYSTEM");
        addressRepository.save(address);

        return Result.success();
    }
}
