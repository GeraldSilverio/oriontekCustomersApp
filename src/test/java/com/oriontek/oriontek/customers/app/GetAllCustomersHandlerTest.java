package com.oriontek.oriontek.customers.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.oriontek.oriontek.customers.app.application.dtos.customers.CustomerResponseDto;
import com.oriontek.oriontek.customers.app.application.features.customers.query.GetAllCustomer.GetAllCustomersHandler;
import com.oriontek.oriontek.customers.app.application.features.customers.query.GetAllCustomer.GetAllCustomersQuery;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class GetAllCustomersHandlerTest {

    @Mock
    CustomerRepository repository;

    @InjectMocks
    GetAllCustomersHandler handler;

    @Test
    void should_return_customers_page() {

        Page<Customer> page = new PageImpl<>(List.of(mock(Customer.class)));

        when(repository.findAll(any(Pageable.class)))
                .thenReturn(page);

        GetAllCustomersQuery query = new GetAllCustomersQuery(0, 10);

        Result<Page<CustomerResponseDto>> result = handler.handle(query);

        assertTrue(result.isSuccess());
        assertEquals(1, result.getValue().getTotalElements());
    }
}
