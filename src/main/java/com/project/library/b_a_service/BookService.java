package com.project.library.b_a_service;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private BookService bookService;

    @Transactional
    public List<Book> find(String title, String authorNS, String libraryNumber) {
        List<Book> bookList = null;

        if (authorNS.isEmpty() && libraryNumber.isEmpty()) {
            bookList = findByTitle(title);
        } else if (libraryNumber.isEmpty() && title.isEmpty()) {
            List<Author> authorList = authorService.findByAuthor(authorNS);
            bookList = new ArrayList<>();
            for (Author author : authorList) {
                List<Book> bookList1 = author.getBookList();
                for (Book book : bookList1) {
                    bookList.add(book);
                }
            }
        } else if (title.isEmpty() && authorNS.isEmpty()) {
            bookList = entityManager.createQuery("from Book b where b.libraryNumber=" + "'" + libraryNumber + "'"
                    , Book.class).getResultList();
        } else if (authorNS.isEmpty()) {
            bookList = entityManager.createQuery("from Book b where b.title=" + "'" + title + "'" + "" +
                    " and b.libraryNumber=" + "'" + libraryNumber + "'", Book.class).getResultList();
        } else if (libraryNumber.isEmpty()) {
            List<Book> bookList1 = entityManager.createQuery("from Book b where b.title=" + "'" + title + "'"
                    , Book.class).getResultList();

            bookList = findByAuthor(authorNS, bookList1);
        } else {
            bookList = entityManager.createQuery("from Book b where b.title=" + "'" + title + "'" + "" +
                    " and b.libraryNumber=" + "'" + libraryNumber + "'", Book.class).getResultList();
        }
        return bookList.stream().distinct().collect(Collectors.toList());
    }

    private List<Book> findByTitle(String title) {
        List<Book> bookList = entityManager.
                createQuery("from Book b where b.title=" + "'" + title + "'", Book.class)
                .getResultList();

        return bookList;
    }

    /*jeśli któraś z list autorów z listy książek będzie się zgadzała - zostaw te książki*/
    private List<Book> findByAuthor(String authorSurname, List<Book> bookList) {
        List<Author> authorList = authorService.findByAuthor(authorSurname);

        List<Book> finalList = bookList.stream()
                .filter(b -> b.getAuthorList().equals(authorList))
                .collect(Collectors.toList());
        System.out.println(finalList);
        return finalList;
    }

    private List<Book> findByLibraryNumber(String libraryNumber) {
        List<Book> bookList = entityManager.createQuery("from Book b where b.libraryNumber=" + "'" + libraryNumber + "'", Book.class)
                .getResultList();
        return bookList;
    }
}
