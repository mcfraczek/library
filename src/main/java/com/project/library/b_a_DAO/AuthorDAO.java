package com.project.library.b_a_DAO;

import com.project.library.a_entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO extends CrudRepository<Author, Integer> {
    Optional<Author> findAuthorByNameAndSurname(String name, String surname);

    List<Author> findAuthorsByName(String name);

    List<Author> findAuthorsBySurname(String surname);
}
