package com.oriontek.oriontek.customers.app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.CustomerEntity;

@Component
public class CustomerMapper {

    public Customer toDomain(CustomerEntity entity) {
        return Customer.create(entity.getName(), entity.getLastName(), entity.getEmail(), entity.getIdentificationNumber(),entity.getIdentificationType());
    }

    public CustomerEntity toEntity(Customer domain) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setLastName(domain.getLastName());
        entity.setEmail(domain.getEmail());
        entity.setIdentificationNumber(domain.getIdentificationNumber());
        entity.setIdentificationType(domain.getIdentificationType());
        entity.setDeleted(domain.isDeleted());
        return entity;
    }
}

