package com.oriontek.oriontek.customers.app.domain.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oriontek.oriontek.customers.app.domain.Models.Customer;

public interface CustomerRepository {

    boolean existsByEmail(String email);

    boolean existsByIdentificationNumber(String identificationNumber);

    Customer save(Customer customer);

    Optional<Customer> findById(UUID id);

    Page<Customer> findAll(Pageable pageable);
}
