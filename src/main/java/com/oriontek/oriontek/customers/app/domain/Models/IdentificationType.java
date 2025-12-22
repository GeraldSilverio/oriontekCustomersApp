package com.oriontek.oriontek.customers.app.domain.Models;

import java.util.UUID;

public class IdentificationType {

    private UUID id;
    private String description;
    private boolean isDeleted;

    public IdentificationType(UUID type, String description) {
        this.id = type;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
