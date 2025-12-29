package com.oriontek.oriontek.customers.app.infrastructure.persistence.repositories.address;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.oriontek.oriontek.customers.app.domain.Models.Address;
import com.oriontek.oriontek.customers.app.domain.repositories.AddressRepository;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.AddressEntity;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.CustomerEntity;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.mapper.AddressMapper;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.repositories.customer.JpaCustomerRepository;

@Repository
public class AddressRepositoryAdapter implements AddressRepository {

    private final JpaAddressRepository jpaAddressRepository;
    private final JpaCustomerRepository jpaCustomerRepository;

    public AddressRepositoryAdapter(
            JpaAddressRepository jpaAddressRepository,
            JpaCustomerRepository jpaCustomerRepository
    ) {
        this.jpaAddressRepository = jpaAddressRepository;
        this.jpaCustomerRepository = jpaCustomerRepository;
    }

    @Override
    public Address save(Address address) {

        CustomerEntity customerEntity = jpaCustomerRepository
                .findById(address.getCustomerId())
                .orElseThrow(() ->
                        new IllegalStateException("Customer not found: " + address.getCustomerId())
                );

        AddressEntity savedEntity = jpaAddressRepository.save(
                AddressMapper.toEntity(address, customerEntity)
        );

        return AddressMapper.toDomain(savedEntity);
    }

    @Override
    public List<Address> findByCustomerId(UUID customerId) {
        return jpaAddressRepository
                .findByCustomer_IdCustomerAndIsDeletedFalse(customerId)
                .stream()
                .map(AddressMapper::toDomain)
                .toList();
    }
}
