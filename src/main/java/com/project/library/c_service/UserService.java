package com.project.library.c_service;

import com.project.library.a_entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    List<User> getUsers();

    User getUser(int id);
}
