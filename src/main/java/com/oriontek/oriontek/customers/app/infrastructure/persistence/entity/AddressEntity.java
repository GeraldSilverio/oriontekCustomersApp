package com.oriontek.oriontek.customers.app.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class AddressEntity {

    @Id
    @Column(name = "id_address")
    private UUID idAddress;
    private String street;
    private String city;
    private String country;
    private boolean isPrincipal;
    private boolean isDeleted;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private CustomerEntity customer;

    public AddressEntity() {}
}
