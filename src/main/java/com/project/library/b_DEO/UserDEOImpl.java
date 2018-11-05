package com.project.library.b_DEO;

import com.project.library.a_entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDEOImpl implements UserDEO {
    @Autowired
    EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = entityManager.createQuery("from User u order by u.surname").getResultList();
        return userList;
    }

    @Override
    public User getUser(int id) {
        User user = entityManager.createQuery("from User u where u.id=" + id, User.class).getSingleResult();
        return user;
    }
}
