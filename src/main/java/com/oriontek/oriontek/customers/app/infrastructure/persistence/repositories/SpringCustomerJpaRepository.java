package com.oriontek.oriontek.customers.app.infrastructure.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.CustomerEntity;

public interface SpringCustomerJpaRepository
        extends JpaRepository<CustomerEntity, UUID> {
}
