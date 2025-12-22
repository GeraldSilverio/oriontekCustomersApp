package com.oriontek.oriontek.customers.app.domain.Models;

import java.util.UUID;

public class Address {

    private UUID id;
    private String street;
    private String city;
    private String country;
    private boolean principal;
    private boolean isDeleted;

    public Address(UUID id, String street, String city, String country, boolean principal) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.country = country;
        this.principal = principal;
    }

    public UUID getId() {
        return id;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setAsPrincipal() {
        this.principal = true;
    }

    public void unsetPrincipal() {
        this.principal = false;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
