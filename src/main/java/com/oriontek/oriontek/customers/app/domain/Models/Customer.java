package com.oriontek.oriontek.customers.app.domain.Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer extends AuditableEntity {

    private UUID idCustomer;
    private String firstName;
    private String lastName;
    private String email;
    private String identificationNumber;
    private int identificationType;
    private boolean isDeleted;
    private List<Address> addresses;

    protected Customer() {
    }

    public Customer(
            UUID idCustomer,
            String firstName,
            String lastName,
            String email,
            String identificationNumber,
            int identificationType,
            List<Address> addresses,
            String createdBy,
            LocalDateTime createdAt) {
        this.idCustomer = idCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.identificationNumber = identificationNumber;
        this.identificationType = identificationType;
        this.addresses = addresses;
        this.isDeleted = false;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Customer(
            UUID idCustomer,
            String firstName,
            String lastName,
            String email,
            String identificationNumber,
            int identificationType,
            List<Address> addresses,
            boolean isDeleted,
            String createdBy,
            LocalDateTime createdAt,
            String updatedBy,
            LocalDateTime updatedAt) {
        this.idCustomer = idCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.identificationNumber = identificationNumber;
        this.identificationType = identificationType;
        this.addresses = addresses;
        this.isDeleted = isDeleted;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    public void update(
            String firstname,
            String lastName,
            String email,
            String identificationNumber,
            int identificationType,
            String modifiedBy,
            LocalDateTime modifiedAt) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.email = email;
        this.identificationNumber = identificationNumber;
        this.identificationType = identificationType;
        this.updatedBy = modifiedBy;
        this.updatedAt = modifiedAt;
    }

    public void softDelete(String updatedBy) {
        this.isDeleted = true;
        this.updatedBy = updatedBy;
        this.updatedAt = LocalDateTime.now();
    }
}
