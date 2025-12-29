package com.oriontek.oriontek.customers.app.domain.repositories;

import java.util.List;
import java.util.UUID;

import com.oriontek.oriontek.customers.app.domain.Models.Address;

public interface AddressRepository {

    Address save(Address address);

    List<Address> findByCustomerId(UUID customerId);

}
