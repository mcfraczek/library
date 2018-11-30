package com.project.library.b_a_DAO;

import com.project.library.a_entity.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressDEO extends CrudRepository<Address, Integer> {
    Optional<Address> findAddressByStreetAndStreetNumberAndApartmentNumberAndCountyAndPostalCodeAndCityIgnoreCase(String street, String streetNumber, String apartmentNumber, String county, String postalCode, String city);
}