package com.oriontek.oriontek.customers.app.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity {

    @Id
    @Column(name = "id_customer")
    private UUID idCustomer;
    private String firstName;
    private String lastName;
    private String email;
    private String identificationNumber;
    private int identificationType;
    private boolean isDeleted;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressEntity> addresses;

    public CustomerEntity() {}
}