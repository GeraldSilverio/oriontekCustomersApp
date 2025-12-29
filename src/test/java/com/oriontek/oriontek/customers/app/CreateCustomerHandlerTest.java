package com.oriontek.oriontek.customers.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.oriontek.oriontek.customers.app.application.features.customers.command.CreateCustomer.CreateCustomerAddressCommand;
import com.oriontek.oriontek.customers.app.application.features.customers.command.CreateCustomer.CreateCustomerCommand;
import com.oriontek.oriontek.customers.app.application.features.customers.command.CreateCustomer.CreateCustomerHandler;
import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.application.validation.Validator;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CreateCustomerHandlerTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    Validator<CreateCustomerCommand> validator;

    @InjectMocks
    CreateCustomerHandler handler;

    @Test
    void should_create_customer_successfully() {

        CreateCustomerCommand command = new CreateCustomerCommand(
                "John",
                "Doe",
                "john@test.com",
                "123",
                1,
                List.of(
                        new CreateCustomerAddressCommand(
                                "Street",
                                "City",
                                "Country",
                                true
                        )
                )
        );

        when(validator.validate(command))
                .thenReturn(Result.success());

        Result<Void> result = handler.handle(command);

        assertTrue(result.isSuccess());
        verify(customerRepository).save(any());
    }

    @Test
    void should_fail_when_validation_fails() {

        CreateCustomerCommand command = mock(CreateCustomerCommand.class);

        when(validator.validate(command))
                .thenReturn(Result.failure(
                        new Error("VALIDATION_ERROR", "Invalid data")));

        Result<Void> result = handler.handle(command);

        assertTrue(result.isFailure());
        verify(customerRepository, never()).save(any());
    }
}
