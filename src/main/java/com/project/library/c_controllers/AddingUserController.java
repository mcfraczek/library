package com.project.library.c_controllers;

import com.project.library.ab_helperBackingBeans.user.UserPlusList;
import com.project.library.b_b_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class AddingUserController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/addUser")
    private String showForm(Model model, UserPlusList user) {
        model.addAttribute("user", user);
        return "addUserForm";
    }

    @PostMapping("/processAddUserForm")
    private String processForm(@Valid @ModelAttribute("user") UserPlusList userPlusList, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            userPlusList.setUser(null);
            model.addAttribute("user", userPlusList);
            return "addUserForm";
        }
        userService.saveUser(userPlusList.getUser());
        return "addUserForm";
    }
}
