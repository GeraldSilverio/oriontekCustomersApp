package com.oriontek.oriontek.customers.app.application.dtos.address;

public record UpdateAddressRequestDto(String street,
        String city,
        String country,
        boolean principal) {

}
