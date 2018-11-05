package com.project.library.b_DEO;

import com.project.library.a_entity.User;

import java.util.List;

public interface UserDEO {

    void saveUser(User user);

    List<User> getUsers();

    User getUser(int id);
}
