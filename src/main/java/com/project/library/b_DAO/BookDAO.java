package com.project.library.b_DAO;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDAO extends CrudRepository<Book, Integer> {
    List<Book> findBooksByLibraryNumber(String libraryNumber);

    List<Book> findBooksByTitle(String title);


    List<Book> findBooksByTypeListContains(String type);

    @Query(value = "")
    List<Book> findBooksByAuthorListContains(List<Author> authorList);
}
