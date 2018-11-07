package com.project.library.d_controllers;

import com.project.library.ab_helperBackingBeans.book.BookPlus;
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
    public ModelAndView showForm(BookPlus book) {
        return new ModelAndView("addBookForm", "book", book);
    }

    @RequestMapping("/processAddBookForm")
    public String processForm(@ModelAttribute("book") BookPlus book) {
        bookService.saveBook(book.getBook());
        return "bookSaved";
    }

}
