package com.project.library.a_entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/*@NotNull(message = "is required")
    @Size(min = 4, message = "is required")*/
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(max = 60, message = "Title name must not exceed 45 characters")
    @Column(name = "title")
    private String title;
    @NotNull
    @Size(min = 13, max = 13, message = "ISBN must be exactly 13 characters long")
    @Column(name = "isbn")
    private String ISBN;
    @NotNull
    @Size(min = 13, max = 13, message = "Library number must be exactly 13 characters long")
    @Column(name = "library_number")
    private String libraryNumber;
    @Column(name = "time_stamp")
    private String date;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "book_type", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> typeList;
    @Valid
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authorList;
    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private User user;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
