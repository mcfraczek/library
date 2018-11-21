package com.project.library.c_controllers;

import com.project.library.a_entity.Book;
import com.project.library.a_entity.User;
import com.project.library.b_DAO.UserDAO;
import com.project.library.b_a_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BorrowingBookController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;

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
    public String borrowingBookDetails(@RequestParam("userId") int userId, Model model) {
        /*show user By id*/
        Optional<User> user = userDAO.findById(userId);
        List<Book> booksBorrowedByUser = user.get().getBookList();
        model.addAttribute("books", booksBorrowedByUser);
        return "borrowingBookDetails";
    }
}