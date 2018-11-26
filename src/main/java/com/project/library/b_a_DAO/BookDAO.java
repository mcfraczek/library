package com.project.library.b_a_DAO;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDAO extends CrudRepository<Book, Integer> {
    List<Book> findBooksByLibraryNumber(String libraryNumber);

    List<Book> findBooksByTitle(String title);


    List<Book> findBooksByTypeListContains(String type);

    List<Book> findBooksByTitleAndAuthorList(String string, List<Author> authorList);
}
