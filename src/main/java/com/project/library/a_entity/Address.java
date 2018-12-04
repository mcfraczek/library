package com.project.library.a_entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(max = 40, message = "Street name must not exceed 40 characters")
    @Column(name = "street")
    private String street;
    @NotNull
    @Size(max = 6, message = "Street number must not exceed 6 characters")
    @Column(name = "street_number")
    private String streetNumber;
    @Size(max = 6, message = "Apartment number must not exceed 6 characters")
    @Column(name = "apartment_number")
    private String apartmentNumber;
    @NotNull
    @Size(max = 30, message = "County name must not exceed 30 characters")
    @Column(name = "county")
    private String county;
    @NotNull
    @Size(min = 6, max = 6, message = "Postal Code must be exactly 6 characters long")
    @Column(name = "postal_code")
    private String postalCode;
    @NotNull
    @Size(max = 30, message = "City name must not exceed 30 characters")
    @Column(name = "city")
    private String city;
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserDetails> userList;
}



