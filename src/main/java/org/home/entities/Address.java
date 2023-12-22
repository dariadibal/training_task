package org.home.entities;

public class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String county;
    private final String zip;
    private final String telephone;

    public Address(String street, String city, String state, String county, String zip, String telephone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.county = county;
        this.zip = zip;
        this.telephone = telephone;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCounty() {
        return county;
    }

    public String getZip() {
        return zip;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getStreet() {
        return street;
    }
}
