package com.project.library.b_b_service;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import com.project.library.a_entity.Type;
import com.project.library.b_a_DAO.BookDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
            bookList = bookDAO.findBooksByTypeListContains(genre);
        } else if (genre.isEmpty()) {
            bookList = bookDAO.findBooksByTitleAndAuthorList(title, authorService.findByAuthor(authorNS));
            /*mamy tytuł i autora  gatunku, nie mamy gatunku*/
        } else if (true) {
            /*mamy tytuł i gatunek, nie mamy autora */
        } else if (true) {
            /*mamy autora i gatunek, nie mamy tytułu*/
        } else if (true) {
            /*mamy tytuł autora i gatunek*/
        }
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
        System.out.println(finalList);
        return finalList;
    }

    private List<Book> findByLibraryNumber(String libraryNumber) {
        List<Book> bookList = entityManager.createQuery("from Book b where b.libraryNumber=" + "'" + libraryNumber + "'", Book.class)
                .getResultList();
        return bookList;
    }

    @Transactional
    public void saveBook(Book book) {
        System.out.println(book.getAuthorList());

        List<Author> authorList = book.getAuthorList().stream().map(author -> {
            Optional<Author> theAuthorOptional = authorService.findAuthorByNameAndSurname
                    (author.getName(), author.getSurname());
            if (theAuthorOptional.isPresent()) {
                return theAuthorOptional.get();
            }
            return author;
        }).collect(Collectors.toList());
        book.setAuthorList(authorList);

        List<Type> typeList = book.getTypeList().stream().map(type -> {
            Optional<Type> typeOptional = typeService.findTypeByType(type.getType());
            if (typeOptional.isPresent()) {
                return typeOptional.get();
            }
            return type;
        }).collect(Collectors.toList());

        book.setTypeList(typeList);
        bookDAO.save(book);
    }
}
