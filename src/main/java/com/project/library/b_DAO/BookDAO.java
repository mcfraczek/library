package com.project.library.b_DAO;

import com.project.library.a_entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDAO extends CrudRepository<Book, Integer> {
}
