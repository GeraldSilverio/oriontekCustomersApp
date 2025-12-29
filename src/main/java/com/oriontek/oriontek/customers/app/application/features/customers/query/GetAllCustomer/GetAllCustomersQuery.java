package com.oriontek.oriontek.customers.app.application.features.customers.query.GetAllCustomer;

import org.springframework.data.domain.Page;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Query;
import com.oriontek.oriontek.customers.app.application.dtos.customers.CustomerResponseDto;

public record GetAllCustomersQuery(
        int page,
        int size
) implements Query<Page<CustomerResponseDto>> {
}