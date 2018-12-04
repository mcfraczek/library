package com.project.library.b_a_DAO;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import com.project.library.a_entity.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookDAO extends CrudRepository<Book, Integer> {
    List<Book> findBooksByLibraryNumber(String libraryNumber);

    List<Book> findBooksByTitleIgnoreCase(String title);

    List<Book> findBooksByAuthorListAndTypeListContaining(List<Author> authorList, Type type);

    List<Book> findBooksByTypeListContaining(Type type);

    List<Book> findBooksByTitleIgnoreCaseAndAuthorList(String string, List<Author> authorList);

    List<Book> findBooksByTitleIgnoreCaseAndTypeListContaining(String title, Type type);

    List<Book> findBooksByTitleIgnoreCaseAndAuthorListAndTypeListContaining(String title, List<Author> authorList, Type type);

    Optional<Book> findBookByLibraryNumberIgnoreCase(String libraryNumber);

    Optional<Book> findBookByISBN(String ISBN);
}

