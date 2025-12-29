package com.oriontek.oriontek.customers.app.application.features.customers.query.GetAllCustomer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.QueryHandler;
import com.oriontek.oriontek.customers.app.application.dtos.customers.CustomerResponseDto;
import com.oriontek.oriontek.customers.app.application.result.Error;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.mapper.CustomerMapper;

@Component("GetAllCustomersQueryHandler")
public class GetAllCustomersHandler
        implements QueryHandler<GetAllCustomersQuery, Page<CustomerResponseDto>> {

    private final CustomerRepository repository;

    public GetAllCustomersHandler(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<Page<CustomerResponseDto>> handle(GetAllCustomersQuery query) {

        Page<CustomerResponseDto> page = repository.findAll(
                PageRequest.of(query.page(), query.size()))
                .map(CustomerMapper::toDto);

        if (page.isEmpty()) {
            return Result.failure(new Error("NOT_FOUND", "Customers not found"));
        }

        return Result.success(page);
    }
}
