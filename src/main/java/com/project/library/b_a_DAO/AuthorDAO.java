package com.project.library.b_a_DAO;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO extends CrudRepository<Author, Integer> {
    Optional<Author> findAuthorByNameAndSurnameIgnoreCase(String name, String surname);

    List<Author> findAuthorsByNameIgnoreCase(String name);

    List<Author> findAuthorsBySurnameIgnoreCase(String surname);

    List<Author> findAuthorsByBookListContaining(Book book);
}
