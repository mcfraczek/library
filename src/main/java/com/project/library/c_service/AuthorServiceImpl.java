package com.project.library.c_service;

import com.project.library.a_entity.Author;
import com.project.library.b_DEO.AuthorDEE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDEE authorDEO;

    @Override
    public void saveAuthor(Author author) {
        authorDEO.save(author);
    }
}
