package com.project.library.b_a_service;

import com.project.library.a_entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<User> showUsers(String name, String surname) {
        List<User> userList = entityManager.createQuery
                ("from User u where u.name='" + name + "' and u.surname=" + "'" + surname + "'", User.class)
                .getResultList();
        return userList;
    }
}
