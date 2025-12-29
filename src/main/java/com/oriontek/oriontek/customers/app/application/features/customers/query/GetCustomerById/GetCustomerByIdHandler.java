package com.oriontek.oriontek.customers.app.application.features.customers.query.GetCustomerById;

import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.QueryHandler;
import com.oriontek.oriontek.customers.app.application.dtos.customers.CustomerResponseDto;
import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.mapper.CustomerMapper;

@Component("GetCustomerByIdQueryHandler")
public class GetCustomerByIdHandler
        implements QueryHandler<GetCustomerByIdQuery, CustomerResponseDto> {

    private final CustomerRepository repository;

    public GetCustomerByIdHandler(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<CustomerResponseDto> handle(GetCustomerByIdQuery query) {

        return repository.findById(query.customerId())
                .map(CustomerMapper::toDto)
                .map(Result::success)
                .orElseGet(() -> Result.failure(
                        new Error("NOT_FOUND", "Customer not found")));
    }
}
