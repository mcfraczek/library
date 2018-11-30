package com.project.library.b_b_service;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import com.project.library.a_entity.Type;
import com.project.library.b_a_DAO.BookDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private AuthorService authorService;
    private EntityManager entityManager;
    private BookDAO bookDAO;
    private TypeService typeService;

    private static boolean ifIwantThisBook(List<Author> authorList, Book book) {
        for (Author author : authorList) {
            if (!author.getBookList().contains(book)) {
                return false;
            }
        }
        return true;
    }

    @Transactional/*(readOnly = true)*//*tylko do czytania*/
    public List<Book> find(String title, String authorNS, String libraryNumber, String genre) {
        List<Book> bookList = null;
        /*jeśli nr biblioteczny jest - szukaj po nim
         * potem nie jest brany pod uwagę*/
        if (!libraryNumber.isEmpty()) {
            bookList = bookDAO.findBooksByLibraryNumber(libraryNumber);
        } else if (authorNS.isEmpty() && genre.equals("Choose...")) {
            bookList = new ArrayList<>();
            /*mamy tytuł*/
            bookList = bookDAO.findBooksByTitle(title);
        } else if (title.isEmpty() && genre.equals("Choose...")) {
            bookList = new ArrayList<>();
            /*mamy autorów*/
            List<Author> authorList = authorService.findByAuthor(authorNS);
            bookList = getBooksFromAuthorList(authorList);
            if (bookList.isEmpty()) {
                bookList = getBooksFromAuthorList(authorService.findByAuthorsName(authorNS));
                /*szukaj czy to są tylko nazwiska*/
                /*jeśli nadal jest pusta - zobacz, czy to nie tylko imiona*/
            }
            if (bookList.isEmpty()) {
                bookList = getBooksFromAuthorList(authorService.findByAuthorsSurname(authorNS));
            }
        } else if (title.isEmpty() && authorNS.isEmpty()) {
            /*find by type*/
            bookList = new ArrayList<>();
            Optional<Type> typeOptional = typeService.findTypeByType(genre);
            if (typeOptional.isPresent()) {
                bookList = bookDAO.findBooksByTypeListContaining(typeOptional.get());
                System.out.println(bookList);
            }
        } else if (genre.isEmpty()) {
            bookList = new ArrayList<>();
            bookList = bookDAO.findBooksByTitleAndAuthorList(title, authorService.findByAuthor(authorNS));
            /*mamy tytuł i autora  gatunku, nie mamy gatunku*/
        } else if (authorNS.isEmpty()) {
            bookList = new ArrayList<>();
            Optional<Type> typeOptional = typeService.findTypeByType(genre);
            if (typeOptional.isPresent()) {
                bookList = bookDAO.findBooksByTitleAndTypeListContaining(title, typeOptional.get());
            }
            /*mamy tytuł i gatunek, nie mamy autora */
        } else if (title.isEmpty()) {
            bookList = new ArrayList<>();
            List<Author> authorList = authorService.findByAuthor(authorNS);
            Optional<Type> typeOptional = typeService.findTypeByType(genre);
            if (typeOptional.isPresent()) {
                bookDAO.findBooksByAuthorListAndTypeListContaining(authorList, typeOptional.get());
            }
        } else {
            bookList = new ArrayList<>();
            List<Author> authorList = authorService.findByAuthor(authorNS);
            Optional<Type> typeOptional = typeService.findTypeByType(genre);
            if (typeOptional.isPresent()) {
                bookList = bookDAO.findBooksByTitleAndAuthorListAndTypeListContaining(title, authorList, typeOptional.get());
            }
        }
        if (bookList == null) {
            /*może inne parametry*/
        }
        return bookList;
    }

    private List<Book> getBooksFromAuthorList(List<Author> authorList) {
        List<Book> bookList;
        bookList = authorList
                .stream()
                .flatMap(author -> author.getBookList()
                        .stream())
                .filter(b -> ifIwantThisBook(authorList, b)).distinct()
                .collect(Collectors.toList());
        return bookList;
    }

    private List<Book> findByTitle(String title) {
        List<Book> bookList = entityManager.
                createQuery("from Book b where b.title=" + "'" + title + "'", Book.class)
                .getResultList();

        return bookList;
    }

    private List<Book> findByAuthor(String authorSurname, List<Book> bookList) {
        List<Author> authorList = authorService.findByAuthor(authorSurname);

        List<Book> finalList = bookList.stream()
                .filter(b -> b.getAuthorList().equals(authorList))
                .collect(Collectors.toList());
        return finalList;
    }

    private List<Book> findByLibraryNumber(String libraryNumber) {
        List<Book> bookList = entityManager.createQuery("from Book b where b.libraryNumber=" + "'" + libraryNumber + "'", Book.class)
                .getResultList();
        return bookList;
    }

    @Transactional
    public void saveBook(Book book) {
        List<Author> authorList = book.getAuthorList().stream().map(author -> {
            Optional<Author> theAuthorOptional = authorService.findAuthorByNameAndSurname
                    (author.getName(), author.getSurname());
            if (theAuthorOptional.isPresent()) {
                return theAuthorOptional.get();
            }
            return author;
        }).collect(Collectors.toList());
        book.setAuthorList(authorList);
        List<Type> typeList = null;
        if (book.getTypeList() != null) {
            typeList = book.getTypeList().stream().map(type -> {
                Optional<Type> typeOptional = typeService.findTypeByType(type.getType());
                if (typeOptional.isPresent()) {
                    return typeOptional.get();
                }
                return type;
            }).collect(Collectors.toList());
        }

        book.setTypeList(typeList);
        bookDAO.save(book);
    }

    public Optional<Book> findBookById(int borrowedId) {
        return bookDAO.findById(borrowedId);
    }
}
