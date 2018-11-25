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
    private BookDAO bookDEO;

    @RequestMapping("/addBook")
    public ModelAndView showForm(BookPlusList book) {
        return new ModelAndView("addBookForm", "book", book);
    }

    @RequestMapping("/processAddBookForm")
    public String processForm(@ModelAttribute("book") BookPlusList bookPlusList) {
        bookDEO.save(bookPlusList.getBook()); /* jeśli nazwisko autora się pojawi - to problem trzeba to zmienić*/
        return "bookSaved";
    }
}
