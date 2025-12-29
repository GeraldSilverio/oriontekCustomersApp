package com.oriontek.oriontek.customers.app.application.dtos.customers;

import java.util.UUID;

public record AddressResponseDto(
        UUID id,
        String street,
        String city,
        String country,
        boolean principal
) {
}
