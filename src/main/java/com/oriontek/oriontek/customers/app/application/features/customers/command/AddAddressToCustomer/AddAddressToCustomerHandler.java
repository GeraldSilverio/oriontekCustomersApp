package com.oriontek.oriontek.customers.app.application.features.customers.command.AddAddressToCustomer;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.CommandHandler;
import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.domain.Models.Address;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.AddressRepository;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;

@Component("AddAddressToCustomerCommandHandler")
public class AddAddressToCustomerHandler
                implements CommandHandler<AddAddressToCustomerCommand,Void> {

        private final CustomerRepository customerRepository;
        private final AddressRepository addressRepository;
        private final AddAddressToCustomerCommandValidator validator;

        public AddAddressToCustomerHandler(
                        CustomerRepository customerRepository,
                        AddressRepository addressRepository,
                        AddAddressToCustomerCommandValidator validator) {
                this.customerRepository = customerRepository;
                this.addressRepository = addressRepository;
                this.validator = validator;
        }

        @Override
        public Result<Void> handle(AddAddressToCustomerCommand command) {

                Result<Void> validation = validator.validate(command);
                if (validation.isFailure()) {
                        return validation;
                }

                Optional<Customer> optionalCustomer = customerRepository.findById(command.customerId());

                if (optionalCustomer.isEmpty()) {
                        return Result.failure(
                                        new Error("404", "Customer not found"));
                }

                Customer customer = optionalCustomer.get();

                if (command.principal()) {
                        customer.getAddresses().forEach(address -> {
                                if (address.isPrincipal()) {
                                        address.update(
                                                        address.getStreet(),
                                                        address.getCity(),
                                                        address.getCountry(),
                                                        false,
                                                        "SYSTEM");
                                        addressRepository.save(address);
                                }
                        });
                }

                Address newAddress = new Address(
                                UUID.randomUUID(),
                                customer.getIdCustomer(),
                                command.street(),
                                command.city(),
                                command.country(),
                                command.principal(),
                                "SYSTEM",
                                LocalDateTime.now());

                addressRepository.save(newAddress);

                return Result.success();
        }
}
