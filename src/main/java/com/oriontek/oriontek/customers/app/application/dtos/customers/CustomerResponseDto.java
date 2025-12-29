package com.oriontek.oriontek.customers.app.application.dtos.customers;

import java.util.List;
import java.util.UUID;

public record CustomerResponseDto(
        UUID id,
        String name,
        String lastName,
        String email,
        String identificationNumber,
        int identificationType,
        List<AddressResponseDto> addresses) {
}
