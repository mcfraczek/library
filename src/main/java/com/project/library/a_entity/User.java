package com.project.library.a_entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(max = 45, message = "User's name must not exceed 45 characters")
    @Column(name = "name")
    private String name;
    @NotNull
    @Size(max = 45, message = "User's surname must not exceed 45 characters")
    @Column(name = "surname")
    private String surname;
    @NotNull
    @Size(max = 11, min = 11, message = "PESEL must be exactly 11 characters long")
    @Column(name = "PESEL")
    private String pESEL;
    @Valid
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private List<Book> bookList;

    public void addBook(Book book) {
        if (bookList == null) {
            bookList = new ArrayList<>();
        } else {
            bookList.add(book);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pESEL='" + pESEL + '\'' +
                '}';
    }
}
