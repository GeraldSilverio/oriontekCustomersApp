package com.oriontek.oriontek.customers.app.domain.Models;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address extends AuditableEntity{

    private UUID idAddress;
    private UUID customerId;
    private String street;
    private String city;
    private String country;
    private boolean isPrincipal;
    private boolean isDeleted;

    protected Address() {
    }

    public Address(
            UUID idAddress,
            UUID customerId,
            String street,
            String city,
            String country,
            boolean isPrincipal,
            String createdBy,
            LocalDateTime createdAt
    ) {
        this.idAddress = idAddress;
        this.customerId = customerId;
        this.street = street;
        this.city = city;
        this.country = country;
        this.isPrincipal = isPrincipal;
        this.isDeleted = false;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Address(
        UUID idAddress,
        UUID customerId,
        String street,
        String city,
        String country,
        boolean isPrincipal,
        boolean isDeleted,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
    this.idAddress = idAddress;
    this.customerId = customerId;
    this.street = street;
    this.city = city;
    this.country = country;
    this.isPrincipal = isPrincipal;
    this.isDeleted = isDeleted;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.updatedBy = updatedBy;
    this.updatedAt = updatedAt;
}


    public void markAsPrincipal() {
        this.isPrincipal = true;
    }

    public void softDelete(String updatedBy) {
        this.isDeleted = true;
        this.updatedBy = updatedBy;
        this.updatedAt = LocalDateTime.now();
    }

    public void update(
            String street,
            String city,
            String country,
            boolean isPrincipal,
            String updatedBy
    ) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.isPrincipal = isPrincipal;
        this.updatedBy = updatedBy;
        this.updatedAt = LocalDateTime.now();
    }
}
