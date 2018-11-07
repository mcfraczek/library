package com.project.library.d_controllers;

import com.project.library.a_entity.User;
import com.project.library.c_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddingUserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    private String showForm(Model model, User user) {
        model.addAttribute("user", user);
        return "addUserForm";
    }

    @PostMapping("/processAddUserForm")
    private String processForm(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "userSaved";
    }
}
