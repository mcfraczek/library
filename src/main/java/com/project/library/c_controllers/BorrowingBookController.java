package com.project.library.c_controllers;

import com.project.library.a_entity.Book;
import com.project.library.a_entity.User;
import com.project.library.ab_helperBackingBeans.book.BooksPlusList;
import com.project.library.b_DAO.UserDAO;
import com.project.library.b_a_service.BookService;
import com.project.library.b_a_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class BorrowingBookController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;
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
    public String borrowingBookDetails(@RequestParam("userId") int userId, Model model,
                                       HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("id", userId);
        Optional<User> user = userDAO.findById(userId);
        BooksPlusList booksPlusList = new BooksPlusList(user.get().getBookList());

        model.addAttribute("books", booksPlusList);
        return "borrowingBookDetails";
    }

    @RequestMapping("/borrowingBookDetailsShowBooks")
    public ModelAndView borrowingBookDetailsShowBooks(
            @RequestParam("title") String title, @RequestParam("authorNS") String authorNS,
            @RequestParam("libraryNumber") String libraryNumber, @RequestParam("genre") String genre,
            HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");
        Optional<User> user = userDAO.findById(userId);
        user.ifPresent(v -> model.addAttribute("books", new BooksPlusList(v.getBookList())));

        /*wypisz genre*/
        List<Book> bookList = bookService.find(title, authorNS, libraryNumber, genre);
        return new ModelAndView("borrowingBookDetails", "bookList", bookList);
    }
}