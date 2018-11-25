package com.project.library.b_a_service;

import com.project.library.a_entity.Book;
import com.project.library.a_entity.User;
import com.project.library.b_DAO.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BookDAO bookDAO;


    @Transactional
    public List<User> showUsers(String name, String surname) {
        List<User> userList = entityManager.createQuery
                ("from User u where u.name='" + name + "' and u.surname=" + "'" + surname + "'", User.class)
                .getResultList();
        return userList;
    }

    @Transactional
    public List<User> showUsersByName(String name) {
        List<User> userList = entityManager.createQuery
                ("from User u where u.name='" + name + "'", User.class)
                .getResultList();
        return userList;
    }

    @Transactional
    public List<User> showUsersBySurname(String surname) {
        List<User> userList = entityManager.createQuery
                ("from User u where u.surname=" + "'" + surname + "'", User.class)
                .getResultList();
        return userList;
    }

    public List<User> getUsers(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        List<User> userList;
        if (name.isEmpty()) {
            userList = showUsersBySurname(surname);
        } else if (surname.isEmpty()) {
            userList = showUsersByName(name);
        } else {
            userList = showUsers(name, surname);
        }
        return userList;
    }

    public boolean checkIfHeHasThatBook(Optional<User> user, int borrowedId) {
        return user.map(x -> x.getBookList()
                .stream()
                .filter(b -> b.getId() == borrowedId)
                .count() > 0).orElse(false);
    }

    @Transactional
    public void setBook(int borrowedId, Optional<User> user) {
        Optional<Book> book = bookDAO.findById(borrowedId);
        if (book.isPresent() && user.isPresent()) {
            user.get().addBook(book.get());
            book.get().setUser(user.get());
        }
    }
}
