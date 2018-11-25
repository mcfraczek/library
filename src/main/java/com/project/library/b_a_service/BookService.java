package com.project.library.b_a_service;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import com.project.library.b_DAO.BookDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private AuthorService authorService;
    private EntityManager entityManager;
    private BookDAO bookDAO;

    private static boolean ifIwantThisBook(List<Author> authorList, Book book) {
        for (Author author : authorList) {
            if (!author.getBookList().contains(book)) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public List<Book> find(String title, String authorNS, String libraryNumber, String genre) {
        List<Book> bookList = null;
        /*jeśli nr biblioteczny jest - szukaj po nim
         * potem nie jest brany pod uwagę*/
        if (!libraryNumber.isEmpty()) {
            bookList = bookDAO.findBooksByLibraryNumber(libraryNumber);
        } else if (authorNS.isEmpty() && genre.equals("Choose...")) {
            /*mamy tytuł*/
            bookList = bookDAO.findBooksByTitle(title);
        } else if (title.isEmpty() && genre.equals("Choose...")) {
            /*mamy autorów*/
            List<Author> authorList = authorService.findByAuthor(authorNS);
            bookList = authorList
                    .stream()
                    .flatMap(author -> author.getBookList()
                            .stream())
//                    .filter(b -> ifIwantThisBook(authorList, b))
                    .collect(Collectors.toList());
        } else if (title.isEmpty() && authorNS.isEmpty()) {
            /*mamy gatunki*/
            bookList = bookDAO.findBooksByTypeListContains(genre);
        } else if (true) {
            /*jest tytuł i są autorzy*/
        } else if (true) {

            /*jest tytuł i jest gatunek*/
        } else if (true) {
            /*są autorzy i jest nrBiblioteczny*/
        } else if (true) {
            /*są autorzy i jest gatunek*/
        } else if (true) {
            /*jest nr biblioteczny i jest gatunek*/
        } else if (true) {
            /*jest tytuł, autorzy i nrBiblioteczny*/
        } else if (true) {
            /*jest tytuł autorzy i gatunek*/
        }
        return bookList;
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
