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
}



