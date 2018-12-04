package com.project.library.c_controllers;

import com.project.library.ab_helperBackingBeans.book.BookPlusList;
import com.project.library.b_b_service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @PostMapping("/processAddBookForm")
    public String processForm(@Valid @ModelAttribute("book") BookPlusList bookPlusList,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            model.addAttribute("book", bookPlusList);
            return "addBookForm";
        }
        if (bookService.checkIfBookCanBeSaved(bookPlusList.getBook())) {
            bookService.saveBook(bookPlusList.getBook());
        } else {
            bookPlusList.setBook(null);
            model.addAttribute("book", bookPlusList);
            bindingResult.addError(new ObjectError("book", "Book already exist"));
            return "addBookForm";
        }
        return "addBookForm";
    }
}
