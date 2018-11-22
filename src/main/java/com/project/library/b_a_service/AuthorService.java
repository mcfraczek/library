package com.project.library.b_a_service;

import com.project.library.a_entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private EntityManager entityManager;

    public List<Author> findByAuthor(String authorSurname) {
        List<Author> authorList = entityManager.createQuery
                ("from Author a where a.surname=" + "'" + authorSurname + "'", Author.class)
                .getResultList();
        return authorList;
    }
}
