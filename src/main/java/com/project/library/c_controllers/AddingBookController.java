package com.project.library.c_controllers;

import com.project.library.ab_helperBackingBeans.book.BookPlusList;
import com.project.library.b_b_service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddingBookController {
    @Autowired
    private BookService bookService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @RequestMapping("/addBook")
    public ModelAndView showForm(BookPlusList book) {
        return new ModelAndView("addBookForm", "book", book);
    }

    @RequestMapping("/processAddBookForm")
    public String processForm(@ModelAttribute("book") BookPlusList bookPlusList) {
        bookService.saveBook(bookPlusList.getBook());
        return "addBookForm";
    }
}
