package com.project.library.a_entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(max = 20, message = "Telephone number must not exceed 20 characters")
    @Column(name = "tel_number")
    private String telNumber;
    @NotNull
    @Size(max = 40, message = "Email must not exceed 40 characters")
    @Column(name = "email")
    private String email;
    @Valid
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", telNumber='" + telNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
