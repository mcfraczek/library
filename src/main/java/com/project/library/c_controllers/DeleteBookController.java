package com.project.library.c_controllers;

import com.project.library.a_entity.Book;
import com.project.library.ab_helperBackingBeans.book.BookPlusList;
import com.project.library.b_b_service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DeleteBookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/searchForABookShowBooksDeleteBook")
    public String searchForABookShowBooksDeleteBook(@RequestParam("title") String title,
                                                    @RequestParam("authorNS") String authorNS,
                                                    @RequestParam("libraryNumber") String libraryNumber,
                                                    @RequestParam("genre") String genre,
                                                    @RequestParam("bookId") int bookId,
                                                    Model model) {
        BookPlusList bookPlusList = new BookPlusList();
        model.addAttribute("books", bookPlusList);

        List<Book> bookList = bookService.find(title, authorNS, libraryNumber, genre);
        model.addAttribute("bookList", bookList);

        bookService.deleteBook(bookId);
        return "searchForABook";
    }
}
