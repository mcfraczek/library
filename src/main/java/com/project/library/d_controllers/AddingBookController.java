package com.project.library.d_controllers;

import com.project.library.a_entity.Book;
import com.project.library.c_service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddingBookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/addBook")
    public ModelAndView showForm(Book book) {
        return new ModelAndView("addBookForm", "book", book);
    }

    @RequestMapping("/processAddBookForm")
    public String processForm(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "bookSaved";
    }

}
