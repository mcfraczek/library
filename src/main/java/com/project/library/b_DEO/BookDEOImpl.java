package com.project.library.b_DEO;

import com.project.library.a_entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class BookDEOImpl implements BookDEO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public void saveBook(Book book) {
        entityManager.persist(book);
    }
}
