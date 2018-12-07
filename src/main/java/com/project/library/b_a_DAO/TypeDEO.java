package com.project.library.b_a_DAO;

import com.project.library.a_entity.Book;
import com.project.library.a_entity.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TypeDEO extends CrudRepository<Type, Integer> {
    Optional<Type> findTypeByTypeIgnoreCase(String type);

    List<Type> findTypeByBookListContaining(Book book);

    Optional<Type> findTypeByTypeContainingIgnoreCase(String type);
}
