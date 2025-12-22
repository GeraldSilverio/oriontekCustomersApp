package com.oriontek.oriontek.customers.app.infrastructure.persistence.repositories;

import org.springframework.stereotype.Repository;

import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.ICustomerRepository;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.CustomerEntity;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.mapper.CustomerMapper;

@Repository
public class CustomerJpaRepository implements ICustomerRepository {

   private final SpringCustomerJpaRepository jpa;
    private final CustomerMapper mapper;

    public CustomerJpaRepository(
        SpringCustomerJpaRepository jpa,
        CustomerMapper mapper
    ) {
        this.jpa = jpa;
        this.mapper = mapper;
    }
@Override
    public Customer save(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        CustomerEntity saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }
}
