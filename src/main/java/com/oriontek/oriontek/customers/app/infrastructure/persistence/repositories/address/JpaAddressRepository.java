package com.oriontek.oriontek.customers.app.infrastructure.persistence.repositories.address;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.AddressEntity;

public interface JpaAddressRepository extends JpaRepository<AddressEntity, UUID> {

    List<AddressEntity> findByCustomer_IdCustomerAndIsDeletedFalse(UUID customerId);

}
