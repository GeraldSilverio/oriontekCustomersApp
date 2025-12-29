package com.oriontek.oriontek.customers.app.application.dtos.address;

public record AddAddressRequestDto(
        String street,
        String city,
        String country,
        boolean principal
) {}
