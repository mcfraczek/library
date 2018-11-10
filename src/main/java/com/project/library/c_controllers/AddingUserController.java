package com.project.library.c_controllers;

import com.project.library.ab_helperBackingBeans.user.UserPlusList;
import com.project.library.b_DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddingUserController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/addUser")
    private String showForm(Model model, UserPlusList user) {
        model.addAttribute("user", user);
        return "addUserForm";
    }

    @PostMapping("/processAddUserForm")
    private String processForm(@ModelAttribute("user") UserPlusList user) {
        userDAO.save(user.getUser());
        return "userSaved";
    }
}
