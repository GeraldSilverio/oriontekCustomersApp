package com.oriontek.oriontek.customers.app.application.features.customers.command.CreateCustomer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;
import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.CommandHandler;
import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.application.validation.Validator;
import com.oriontek.oriontek.customers.app.domain.Models.Address;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;

@Component("CreateCustomerCommandHandler")
public class CreateCustomerHandler implements CommandHandler<CreateCustomerCommand, Void> {

        private final CustomerRepository customerRepository;
        private final Validator<CreateCustomerCommand> validator;

        public CreateCustomerHandler(CustomerRepository customerRepository,
                        Validator<CreateCustomerCommand> validator) {
                this.customerRepository = customerRepository;
                this.validator = validator;
        }

        @Override
        public Result handle(CreateCustomerCommand command) {

                Result<Void> validation = validator.validate(command);
                if (validation.isFailure()) {
                        return validation;
                }

                boolean result = customerRepository.existsByEmail(command.email());
                if (result) {
                        return Result.failure(
                                        new Error("BUSINESS_RULE", "Email already exists"));
                }

                if (customerRepository.existsByIdentificationNumber(
                                command.identificationNumber())) {
                        return Result.failure(
                                        new Error("BUSINESS_RULE", "Identification number already exists"));
                }
                UUID customerId = UUID.randomUUID();
                LocalDateTime now = LocalDateTime.now();
                String systemUser = "SYSTEM";

                List<Address> addresses = command.addresses()
                                .stream()
                                .map(a -> new Address(
                                                UUID.randomUUID(),
                                                customerId,
                                                a.street(),
                                                a.city(),
                                                a.country(),
                                                a.principal(),
                                                systemUser,
                                                now))
                                .toList();

                Customer customer = new Customer(
                                customerId,
                                command.name(),
                                command.lastName(),
                                command.email(),
                                command.identificationNumber(),
                                command.identificationType(),
                                addresses,
                                systemUser,
                                now);

                customerRepository.save(customer);

                return Result.success();
        }
}
