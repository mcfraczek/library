package com.project.library.c_controllers;

import com.project.library.a_entity.User;
import com.project.library.b_a_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BorrowingBookController {

    @Autowired
    private UserService userService;

    @RequestMapping("/borrowingBookForm")

    public String borrowingBookForm() {
        return "borrowingBookForm";
    }

    @RequestMapping("/borrowingBookFormShowUsers")
    public String borrowingBookFormShowUsers
            (@RequestParam("name") String name, @RequestParam("surname") String surname, Model model) {
        List<User> userList = userService.showUsers(name, surname);
        model.addAttribute("userList", userList);
        return "borrowingBookForm";
    }
}
