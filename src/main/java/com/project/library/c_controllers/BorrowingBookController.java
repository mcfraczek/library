package com.project.library.c_controllers;

import com.project.library.a_entity.Book;
import com.project.library.a_entity.User;
import com.project.library.ab_helperBackingBeans.book.BooksPlusList;
import com.project.library.b_b_service.BookService;
import com.project.library.b_b_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class BorrowingBookController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/borrowingBookForm")
    public String borrowingBookForm() {
        return "borrowingBookForm";
    }

    @RequestMapping("/borrowingBookFormShowUsers")
    public String borrowingBookFormShowUsers
            (@RequestParam("name") String name, @RequestParam("surname") String surname, Model model) {
        if (surname.isEmpty() && name.isEmpty()) {
            return "borrowingBookForm";
        }
        List<User> userList = userService.getUsers(name, surname);
        model.addAttribute("userList", userList);
        return "borrowingBookForm";
    }

    @RequestMapping("/borrowingBookDetails")
    public String borrowingBookDetails(HttpSession session, Model model, @RequestParam("userId") int userId) {
        /*Biorę id urzytkownika, biorę książkim które wyporzyczył i zapisuję id so sesji na przyszłość*/
        session.setAttribute("id", userId);
        Optional<User> user = userService.findById(userId);
        BooksPlusList booksPlusList = new BooksPlusList();
        user.ifPresent(u -> booksPlusList.setBookList(u.getBookList()));
        model.addAttribute("books", booksPlusList);
        return "borrowingBookDetails";
    }

    @RequestMapping("/borrowingBookDetailsShowBooks")
    public String borrowingBookDetailsShowBooks(HttpSession session, Model model,
                                                @RequestParam("title") String title,
                                                @RequestParam("authorNS") String authorNS,
                                                @RequestParam("libraryNumber") String libraryNumber,
                                                @RequestParam("genre") String genre) {
        /*Nadal potrzebuję listy książek, więc biorę ją już z sesji*/
        if (title.isEmpty() && authorNS.isEmpty() && libraryNumber.isEmpty() && genre.equals("Choose...")) {
            int userId = (int) session.getAttribute("id");
            Optional<User> user = userService.findById(userId);
            user.ifPresent(v -> model.addAttribute("books", new BooksPlusList(v.getBookList())));
            model.addAttribute("bookList", null);
            return "borrowingBookDetails";
        }
        int userId = (int) session.getAttribute("id");
        Optional<User> user = userService.findById(userId);
        user.ifPresent(v -> model.addAttribute("books", new BooksPlusList(v.getBookList())));
        List<Book> bookList = bookService.find(title, authorNS, libraryNumber, genre);
        model.addAttribute("bookList", bookList);
        return "borrowingBookDetails";
    }

    @RequestMapping("/borrowingBookDetailsShowBooksBorrow")
    public String borrowingBookDetailsShowBooksBorrow(HttpSession session, Model model,
                                                      @RequestParam("borrowedId") int borrowedId,
                                                      @RequestParam("title") String title,
                                                      @RequestParam("authorNS") String authorNS,
                                                      @RequestParam("libraryNumber") String libraryNumber,
                                                      @RequestParam("genre") String genre) {

        int userId = (int) session.getAttribute("id");
        Optional<User> user = userService.getUserFromIdAndBorrowBook(borrowedId, userId);
        user.ifPresent(v -> model.addAttribute("books", new BooksPlusList(v.getBookList())));
        List<Book> bookList = bookService.find(title, authorNS, libraryNumber, genre);
        model.addAttribute("bookList", bookList);
        return "borrowingBookDetails";
    }

    @RequestMapping("/borrowingBookDetailsReturn")
    public String borrowingBookDetailsReturn(Model model, HttpSession session,
                                             @RequestParam("title") String title,
                                             @RequestParam("authorNS") String authorNS,
                                             @RequestParam("libraryNumber") String libraryNumber,
                                             @RequestParam("genre") String genre,
                                             @RequestParam("returnedId") int returnedId) {
        int userId = (int) session.getAttribute("id");

        userService.returnBook(returnedId, userId);
        Optional<User> user = userService.findById(userId);
        user.ifPresent(v -> model.addAttribute("books", new BooksPlusList(v.getBookList())));
        List<Book> bookList = bookService.find(title, authorNS, libraryNumber, genre);
        model.addAttribute("bookList", bookList);/*dzięki temu po wyszukiwaniu i oddaniu książki,
        nie traci się danych wyszukiwania*/
        return "borrowingBookDetails";
    }

    @RequestMapping("/borrowingBookDetailsProlong")
    public String borrowingBookDetailsProlong(Model model, HttpSession session,
                                              @RequestParam("title") String title,
                                              @RequestParam("authorNS") String authorNS,
                                              @RequestParam("libraryNumber") String libraryNumber,
                                              @RequestParam("genre") String genre,
                                              @RequestParam("returnedId") int returnedId) {
        int userId = (int) session.getAttribute("id");
        Optional<User> user = userService.findById(userId);
        user.ifPresent(v -> model.addAttribute("books", new BooksPlusList(v.getBookList())));
        List<Book> bookList = bookService.find(title, authorNS, libraryNumber, genre);
        model.addAttribute("bookList", bookList);/*dzięki temu po wyszukiwaniu i oddaniu książki,
        nie traci się danych wyszukiwania*/

        userService.prolongBook(returnedId, userId);
        return "borrowingBookDetails";
    }

}