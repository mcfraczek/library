package com.project.library.c_service;

import com.project.library.a_entity.User;
import com.project.library.b_DEO.UserDEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDEO userDEO;

    @Transactional
    @Override
    public void saveUser(User user) {
        userDEO.saveUser(user);
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        return userDEO.getUsers();
    }

    @Transactional
    @Override
    public User getUser(int id) {
        return userDEO.getUser(id);
    }
}
