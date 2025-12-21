package com.oriontek.oriontek.customers.app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {

    private UUID id;
    private String name;
    private String lastName;
    private String email;
    private String identificationNumber;
    private IdentificationType identificationType;
    private List<Address> addresses = new ArrayList<>();

    public Customer(
            UUID id,
            String name,
            String lastName,
            String email,
            String identificationNumber,
            IdentificationType identificationType
    ) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.identificationNumber = identificationNumber;
        this.identificationType = identificationType;
    }
    
    public void addAddress(Address address) {
        if (address.isPrincipal()) {
            addresses.forEach(Address::unsetPrincipal);
        }
        addresses.add(address);
    }

    public void setPrincipalAddress(UUID addressId) {
        addresses.forEach(a -> a.unsetPrincipal());
        addresses.stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .ifPresent(Address::setAsPrincipal);
    }

    public UUID getId() {
        return id;
    }

    public List<Address> getAddresses() {
        return List.copyOf(addresses);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }
}
