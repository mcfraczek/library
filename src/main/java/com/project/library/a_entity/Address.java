package com.project.library.a_entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "street")
    private String street;
    @Column(name = "street_number")
    private String streetNumber;
    @Column(name = "apartment_number")
    private String apartmentNumber;
    @Column(name = "county")
    private String county;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "city")
    private String city;
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserDetails> userList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (streetNumber != null ? !streetNumber.equals(address.streetNumber) : address.streetNumber != null)
            return false;
        if (apartmentNumber != null ? !apartmentNumber.equals(address.apartmentNumber) : address.apartmentNumber != null)
            return false;
        if (county != null ? !county.equals(address.county) : address.county != null) return false;
        if (postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null) return false;
        return city != null ? city.equals(address.city) : address.city == null;
    }
}



