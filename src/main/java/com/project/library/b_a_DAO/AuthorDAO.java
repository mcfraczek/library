package com.project.library.b_a_DAO;

import com.project.library.a_entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorDAO extends CrudRepository<Author, Integer> {
}
