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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView borrowingBookDetailsShowBooks(HttpSession session, Model model,
                                                      @RequestParam("title") String title,
                                                      @RequestParam("authorNS") String authorNS,
                                                      @RequestParam("libraryNumber") String libraryNumber,
                                                      @RequestParam("genre") String genre) {
        /*Nadal potrzebuję listy książek, więc biorę ją już z sesji*/
        int userId = (int) session.getAttribute("id");
        Optional<User> user = userService.findById(userId);
        user.ifPresent(v -> model.addAttribute("books", new BooksPlusList(v.getBookList())));

        List<Book> bookList = bookService.find(title, authorNS, libraryNumber, genre);

        return new ModelAndView("borrowingBookDetails", "bookList", bookList);
    }

    @RequestMapping("/borrowingBookDetailsShowBooksBorrow")
    public String borrowingBookDetailsShowBooksBorrow(HttpSession session, Model model,
                                                      @RequestParam("borrowedId") int borrowedId,
                                                      @RequestParam("title") String title,
                                                      @RequestParam("authorNS") String authorNS,
                                                      @RequestParam("libraryNumber") String libraryNumber,
                                                      @RequestParam("genre") String genre) {

        int userId = (int) session.getAttribute("id");
        Optional<User> user = userService.findById(userId);
        boolean heHas = userService.checkIfHeHasThatBook(user, borrowedId);
        if (!heHas) {
            userService.setBook(borrowedId, user);
        }
        user.ifPresent(v -> model.addAttribute("books", new BooksPlusList(v.getBookList())));
        List<Book> bookList = bookService.find(title, authorNS, libraryNumber, genre);
        model.addAttribute("booklist", bookList);
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
        Optional<User> user = userService.findById(userId);
        user.ifPresent(v -> model.addAttribute("books", new BooksPlusList(v.getBookList())));
        List<Book> bookList = bookService.find(title, authorNS, libraryNumber, genre);
        model.addAttribute("bookList", bookList);/*dzięki temu po wyszukiwaniu i oddaniu książki,
        nie traci się danych wyszukiwania*/

        userService.returnBook(returnedId, userId);

        return "borrowingBookDetails";
    }

}