package com.oriontek.oriontek.customers.app;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.oriontek.oriontek.customers.app.application.features.customers.query.GetCustomerById.GetCustomerByIdHandler;
import com.oriontek.oriontek.customers.app.application.features.customers.query.GetCustomerById.GetCustomerByIdQuery;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class GetCustomerByIdHandlerTest {

        @Mock
        CustomerRepository repository;

        @InjectMocks
        GetCustomerByIdHandler handler;

        @Test
        void should_return_customer_when_found() {

                UUID id = UUID.randomUUID();
                Customer customer = mock(Customer.class);

                when(repository.findById(id))
                                .thenReturn(Optional.of(customer));

                GetCustomerByIdQuery query = new GetCustomerByIdQuery(id);

                Result<?> result = handler.handle(query);

                assertTrue(result.isSuccess());
        }

        @Test
        void should_fail_when_customer_not_found() {

                UUID id = UUID.randomUUID();

                when(repository.findById(id))
                                .thenReturn(Optional.empty());

                GetCustomerByIdQuery query = new GetCustomerByIdQuery(id);

                Result<?> result = handler.handle(query);

                assertTrue(result.isFailure());
                assertEquals("NOT_FOUND", result.getError().code());
        }
}
