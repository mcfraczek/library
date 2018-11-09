package com.project.library.b_DEO;

import com.project.library.a_entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AuthorDEEImpl implements AuthorDEE {
    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(Author author) {
        entityManager.persist(author);
    }
}
