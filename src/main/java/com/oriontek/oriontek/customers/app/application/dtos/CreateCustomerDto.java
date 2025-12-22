package com.oriontek.oriontek.customers.app.application.dtos;

import java.util.UUID;

public record CreateCustomerDto(String name,String lastName,String email, UUID identificationTypeId) {
} 

