package com.project.library.b_b_service;

import com.project.library.a_entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class QuickSearchService {
    private BookService bookService;

    public List<Book> search(String searched) {
        List<Book> bookList = null;

        bookList = bookService.findFirst("", "", searched, "Choose...");
        if (!bookList.isEmpty()) {
            return bookList;
        }
        bookList = bookService.findFirst(searched, "", "", "Choose...");
        if (!bookList.isEmpty()) {
            return bookList;
        }
        bookList = bookService.findFirst("", searched, "", "Choose...");
        if (!bookList.isEmpty()) {
            return bookList;
        }
        bookList = bookService.findFirst("", "", "", searched);
        if (!bookList.isEmpty()) {
            return bookList;
        }
        bookList = bookService.afterFirstSearchStillNoResults(searched, "", "Choose...");
        if (!bookList.isEmpty()) {
            return bookList;
        }
        bookList = bookService.afterFirstSearchStillNoResults("", searched, "Choose...");
        if (!bookList.isEmpty()) {
            return bookList;
        }
        return new ArrayList<>();
    }
}
