package com.project.library.c_controllers;

import com.project.library.ab_helperBackingBeans.book.BookPlusList;
import com.project.library.b_DAO.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddingBookController {
    @Autowired
    private BookDAO bookService;

    @RequestMapping("/addBook")
    public ModelAndView showForm(BookPlusList book) {
        return new ModelAndView("addBookForm", "book", book);
    }

    @RequestMapping("/processAddBookForm")
    public String processForm(@ModelAttribute("book") BookPlusList book) {
        book.eraseNullAuthors();
        bookService.save(book.getBook());
        return "bookSaved";
    }
}
