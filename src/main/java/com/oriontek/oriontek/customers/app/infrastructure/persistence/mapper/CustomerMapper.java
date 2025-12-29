package com.oriontek.oriontek.customers.app.infrastructure.persistence.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.oriontek.oriontek.customers.app.application.dtos.customers.AddressResponseDto;
import com.oriontek.oriontek.customers.app.application.dtos.customers.CustomerResponseDto;
import com.oriontek.oriontek.customers.app.domain.Models.Address;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.AddressEntity;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.CustomerEntity;

public final class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerEntity toEntity(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerEntity entity = new CustomerEntity();

        entity.setIdCustomer(customer.getIdCustomer());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmail());
        entity.setIdentificationNumber(customer.getIdentificationNumber());
        entity.setIdentificationType(customer.getIdentificationType());
        entity.setDeleted(customer.isDeleted());

        entity.setCreatedBy(customer.getCreatedBy());
        entity.setCreatedAt(customer.getCreatedAt());
        entity.setUpdatedBy(customer.getUpdatedBy());
        entity.setUpdatedAt(customer.getUpdatedAt());

        if (customer.getAddresses() != null) {
            List<AddressEntity> addressEntities = customer.getAddresses()
                    .stream()
                    .map(address -> AddressMapper.toEntity(address, entity))
                    .collect(Collectors.toList());

            entity.setAddresses(addressEntities);
        }

        return entity;
    }

    public static Customer toDomain(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }

        List<Address> addresses = entity.getAddresses() == null
                ? List.of()
                : entity.getAddresses()
                        .stream()
                        .filter(address -> !address.isDeleted())
                        .map(AddressMapper::toDomain)
                        .toList();

        return new Customer(
                entity.getIdCustomer(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getIdentificationNumber(),
                entity.getIdentificationType(),
                addresses,
                entity.isDeleted(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedBy(),
                entity.getUpdatedAt());
    }

    public static CustomerResponseDto toDto(Customer customer) {

        return new CustomerResponseDto(
                customer.getIdCustomer(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getIdentificationNumber(),
                customer.getIdentificationType(),
                mapAddresses(customer));
    }

    private static List<AddressResponseDto> mapAddresses(Customer customer) {
        return customer.getAddresses()
                .stream()
                .map(address -> new AddressResponseDto(
                        address.getIdAddress(),
                        address.getStreet(),
                        address.getCity(),
                        address.getCountry(),
                        address.isPrincipal()))
                .toList();
    }
}