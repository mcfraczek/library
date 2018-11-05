package com.project.library.d_controllers;

import com.project.library.a_entity.Address;
import com.project.library.a_entity.User;
import com.project.library.a_entity.UserDetails;
import com.project.library.c_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AddingUserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    private String showForm(Model model, User user, UserDetails userDetails, Address address) {

        List<Object> modelList = new ArrayList<>();
        modelList.add(user);
        modelList.add(userDetails);
        modelList.add(address);
        model.addAttribute("model", model);

        return "addUserform";
    }

    @PostMapping("/processForm")
    private String processForm(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "saved";
    }
}
