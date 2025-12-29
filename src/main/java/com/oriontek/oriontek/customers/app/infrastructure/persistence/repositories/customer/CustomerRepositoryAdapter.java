package com.oriontek.oriontek.customers.app.infrastructure.persistence.repositories.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.oriontek.oriontek.customers.app.domain.Models.Customer;
import com.oriontek.oriontek.customers.app.domain.repositories.CustomerRepository;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.entity.CustomerEntity;
import com.oriontek.oriontek.customers.app.infrastructure.persistence.mapper.CustomerMapper;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final JpaCustomerRepository jpaRepository;

    public CustomerRepositoryAdapter(JpaCustomerRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return CustomerMapper.toDomain(
                jpaRepository.save(CustomerMapper.toEntity(customer)));
    }

    @Override
    public Optional<Customer> findById(UUID customerId) {
        return jpaRepository.findById(customerId)
                .filter(c -> !c.isDeleted())
                .map(CustomerMapper::toDomain);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {

        Page<CustomerEntity> page = jpaRepository.findAllByIsDeletedFalse(pageable);

        return page.map(CustomerMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmailAndIsDeletedFalse(email);
    }

    @Override
    public boolean existsByIdentificationNumber(String identificationNumber) {
        return jpaRepository
                .existsByIdentificationNumberAndIsDeletedFalse(identificationNumber);
    }

}
