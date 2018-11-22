package com.project.library.b_a_service;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Book> find(String title, String author, String libraryNumber) {
        List<Book> bookList = null;

        if (author.isEmpty() && libraryNumber.isEmpty()) {
            bookList = findByTitle(title);
        } else if (author.isEmpty()) {
            bookList = entityManager.createQuery("from Book b where b.title=" + "'" + title + "'" + "" +
                    " and b.libraryNumber=" + "'" + libraryNumber + "'", Book.class).getResultList();
        } else if (libraryNumber.isEmpty()) {
            List<Book> bookList1 = entityManager.createQuery("from Book b where b.title=" + "'" + title + "'"
                    , Book.class).getResultList();
            bookList = findByAuthor(author, bookList1);
        } else {
            bookList = entityManager.createQuery("from Book b where b.title=" + "'" + title + "'" + "" +
                    " and b.libraryNumber=" + "'" + libraryNumber + "'", Book.class).getResultList();
        }
        return bookList;
    }

    private List<Book> findByTitle(String title) {
        List<Book> bookList = entityManager.
                createQuery("from Book b where b.title=" + "'" + title + "'", Book.class)
                .getResultList();

        return bookList;
    }

    private List<Book> findByAuthor(String author, List<Book> bookList) {
        List<Author> authorList = authorService.findByAuthor(author);
        List<Author> authorListFromBook = null;
        for (Book book : bookList) {
            authorListFromBook = book.getAuthorList();
        }
        System.out.println(authorList.equals(authorListFromBook));
        List<Book> bookList2 = null;
        /*filtr nie wiem jak*/
        return bookList;
    }

    private List<Book> findByLibraryNumber(String libraryNumber) {
        List<Book> bookList = entityManager.createQuery("from Book b where b.libraryNumber=" + "'" + libraryNumber + "'", Book.class)
                .getResultList();
        return bookList;
    }
}
