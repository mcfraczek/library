package com.project.library.c_controllers;

import com.project.library.a_entity.User;
import com.project.library.ab_helperBackingBeans.methods.MyError;
import com.project.library.b_b_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DeleteUserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/borrowingBookDetailsDeleteUser")
    public String borrowingBookDetailsDeleteUser(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("userId") int userId, Model model) {

        if (userService.userDontHaveBooks(userId)) {
            userService.deleteUser(userId);
        } else {
            model.addAttribute("myError", new MyError("User must return books, before he is deleted", userId));
        }
        /*Żeby lista nie zgasła*/
        List<User> userList = userService.getUsers(name, surname);
        model.addAttribute("userList", userList);
        return "borrowingBookForm";
    }

    @RequestMapping("/borrowingBookDetailsForceDeleteUser")
    public String borrowingBookDetailsForceDeleteUser(@RequestParam("name") String name,
                                                      @RequestParam("surname") String surname,
                                                      @RequestParam("userId") int userId, Model model) {
        userService.forceDeleteUser(userId);
        /*Żeby lista nie zgasła*/
        List<User> userList = userService.getUsers(name, surname);
        model.addAttribute("userList", userList);
        return "borrowingBookForm";
    }
}
