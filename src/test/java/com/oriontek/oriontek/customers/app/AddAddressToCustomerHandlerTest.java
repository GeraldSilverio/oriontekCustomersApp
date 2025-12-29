package com.oriontek.oriontek.customers.app;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.oriontek.oriontek.customers.app.application.features.customers.command.AddAddressToCustomer.AddAddressToCustomerCommand;
import com.oriontek.oriontek.customers.app.application.features.customers.command.AddAddressToCustomer.AddAddressToCustomerCommandValidator;
import com.oriontek.oriontek.customers.app.application.features.customers.command.AddAddressToCustomer.AddAddressToCustomerHandler;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.domain.Models.Address;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.AddressRepository;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class AddAddressToCustomerHandlerTest {

        @Mock
        CustomerRepository customerRepository;

        @Mock
        AddressRepository addressRepository;

        @Mock
        AddAddressToCustomerCommandValidator validator;

        @InjectMocks
        AddAddressToCustomerHandler handler;

        @Test
        void should_add_address_to_customer() {

                UUID customerId = UUID.randomUUID();
                Customer customer = mock(Customer.class);

                when(customerRepository.findById(customerId))
                                .thenReturn(Optional.of(customer));

                when(validator.validate(any(AddAddressToCustomerCommand.class)))
                                .thenReturn(Result.success());

                AddAddressToCustomerCommand command = new AddAddressToCustomerCommand(
                                customerId,
                                "Street",
                                "City",
                                "Country",
                                true);

                Result<Void> result = handler.handle(command);

                assertTrue(result.isSuccess());
                verify(addressRepository).save(any(Address.class));
        }

        @Test
        void should_fail_when_customer_not_found() {

                UUID customerId = UUID.randomUUID();

                when(customerRepository.findById(customerId))
                                .thenReturn(Optional.empty());

                when(validator.validate(any(AddAddressToCustomerCommand.class)))
                                .thenReturn(Result.success());

                AddAddressToCustomerCommand command = new AddAddressToCustomerCommand(
                                customerId,
                                "Street",
                                "City",
                                "Country",
                                true);

                Result<Void> result = handler.handle(command);

                assertTrue(result.isFailure());
                assertEquals("404", result.getError().code());
        }

}
