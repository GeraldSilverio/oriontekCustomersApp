package com.oriontek.oriontek.customers.app;

import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.oriontek.oriontek.customers.app.application.features.customers.command.UpdateCustomer.UpdateCustomerCommand;
import com.oriontek.oriontek.customers.app.application.features.customers.command.UpdateCustomer.UpdateCustomerCommandValidator;
import com.oriontek.oriontek.customers.app.application.features.customers.command.UpdateCustomer.UpdateCustomerHandler;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerHandlerTest {

        @Mock
        CustomerRepository repository;

        @InjectMocks
        UpdateCustomerHandler handler;

        @Mock
        UpdateCustomerCommandValidator validator;

        @Test
        void should_update_customer() {

                UUID id = UUID.randomUUID();
                Customer customer = mock(Customer.class);

                when(validator.validate(any(UpdateCustomerCommand.class)))
                                .thenReturn(Result.success());

                when(repository.findById(id))
                                .thenReturn(Optional.of(customer));

                UpdateCustomerCommand command = new UpdateCustomerCommand(
                                id,
                                "New",
                                "Name",
                                "new@test.com",
                                "456",
                                2);

                Result<?> result = handler.handle(command);

                assertTrue(result.isSuccess());
                verify(repository).save(customer);
        }
}
