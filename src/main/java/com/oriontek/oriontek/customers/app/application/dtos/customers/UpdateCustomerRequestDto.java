package com.oriontek.oriontek.customers.app.application.dtos.customers;

public record UpdateCustomerRequestDto(
        String name,
        String lastName,
        String email,
        String identificationNumber,
        int identificationType) {
}
