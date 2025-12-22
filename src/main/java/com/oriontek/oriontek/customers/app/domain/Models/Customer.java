package com.oriontek.oriontek.customers.app.domain.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {

    private final UUID id;
    private String name;
    private String lastName;
    private String email;
    private String identificationNumber;
    private int identificationType;
    private final List<Address> addresses;
    private boolean deleted;

    private Customer(
            UUID id,
            String name,
            String lastName,
            String email,
            String identificationNumber,
            int identificationType) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.identificationNumber = identificationNumber;
        this.identificationType = identificationType;
        this.addresses = new ArrayList<>();
        this.deleted = false;
    }

    public static Customer create(
            String name,
            String lastName,
            String email,
            String identificationNumber,
            int identificationType) {
        return new Customer(
                UUID.randomUUID(),
                name,
                lastName,
                email,
                identificationNumber,
                identificationType);

    }

    public UUID getId() {
        return id;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(Integer identificationType) {
        this.identificationType = identificationType;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}
