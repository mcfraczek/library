package com.project.library.c_service;

import com.project.library.a_entity.Book;
import com.project.library.b_DEO.BookDEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDEO bookDEO;

    @Override
    @Transactional
    public void saveBook(Book book) {
        bookDEO.saveBook(book);
    }
}
