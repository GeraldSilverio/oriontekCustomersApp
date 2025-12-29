package com.oriontek.oriontek.customers.app.infrastructure.persistence.mapper;

import com.oriontek.oriontek.customers.app.domain.Models.Address;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.AddressEntity;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.CustomerEntity;

public final class AddressMapper {

    private AddressMapper() {
    }

    public static AddressEntity toEntity(Address address, CustomerEntity customerEntity) {
        if (address == null) {
            return null;
        }

        AddressEntity entity = new AddressEntity();

        entity.setIdAddress(address.getIdAddress());
        entity.setStreet(address.getStreet());
        entity.setCity(address.getCity());
        entity.setCountry(address.getCountry());
        entity.setPrincipal(address.isPrincipal());
        entity.setDeleted(address.isDeleted());

        // Audit
        entity.setCreatedBy(address.getCreatedBy());
        entity.setCreatedAt(address.getCreatedAt());
        entity.setUpdatedBy(address.getUpdatedBy());
        entity.setUpdatedAt(address.getUpdatedAt());

        entity.setCustomer(customerEntity);

        return entity;
    }

    public static Address toDomain(AddressEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Address(
                entity.getIdAddress(),
                entity.getCustomer().getIdCustomer(),
                entity.getStreet(),
                entity.getCity(),
                entity.getCountry(),
                entity.isPrincipal(),
                entity.isDeleted(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedBy(),
                entity.getUpdatedAt()
        );
    }
}