package com.oriontek.oriontek.customers.app.domain.repositories;

import com.oriontek.oriontek.customers.app.domain.Models.Customer;

public interface ICustomerRepository {
    Customer save(Customer customer);
    
}
