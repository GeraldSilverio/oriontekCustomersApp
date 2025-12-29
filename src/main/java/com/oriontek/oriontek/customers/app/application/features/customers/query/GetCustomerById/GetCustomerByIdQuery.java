package com.oriontek.oriontek.customers.app.application.features.customers.query.GetCustomerById;

import java.util.UUID;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Query;
import com.oriontek.oriontek.customers.app.application.dtos.customers.CustomerResponseDto;

public record GetCustomerByIdQuery(
        UUID customerId) implements Query<CustomerResponseDto> {
}
