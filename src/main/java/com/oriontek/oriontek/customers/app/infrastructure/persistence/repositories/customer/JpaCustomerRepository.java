package com.oriontek.oriontek.customers.app.infrastructure.persistence.repositories.customer;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.CustomerEntity;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    Page<CustomerEntity> findAllByIsDeletedFalse(Pageable pageable);

    boolean existsByEmailAndIsDeletedFalse(String email);

    boolean existsByIdentificationNumberAndIsDeletedFalse(String identificationNumber);
}
