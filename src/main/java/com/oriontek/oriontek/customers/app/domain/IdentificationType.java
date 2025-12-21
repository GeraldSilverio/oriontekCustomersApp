package com.oriontek.oriontek.customers.app.domain;

import java.util.UUID;

public class IdentificationType {

    private UUID id;
    private String description;

    public IdentificationType(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
