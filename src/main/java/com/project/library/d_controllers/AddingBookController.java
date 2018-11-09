package com.project.library.d_controllers;

import com.project.library.a_entity.Author;
import com.project.library.ab_helperBackingBeans.book.BookPlusList;
import com.project.library.c_service.AuthorService;
import com.project.library.c_service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AddingBookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @RequestMapping("/addBook")
    public ModelAndView showForm(BookPlusList book) {
        return new ModelAndView("addBookForm", "book", book);
    }

    @RequestMapping("/processAddBookForm")
    public String processForm(@ModelAttribute("book") BookPlusList book) {
        List<Author> authorList = book.getBook().getAuthorList();
        for (Author author : authorList) {
            author.addBook(book.getBook());
            authorService.saveAuthor(author);
        }
        bookService.saveBook(book.getBook());

        return "bookSaved";
    }

}
