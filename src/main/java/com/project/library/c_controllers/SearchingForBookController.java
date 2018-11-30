package com.project.library.c_controllers;

import com.project.library.a_entity.Book;
import com.project.library.ab_helperBackingBeans.book.BookPlusList;
import com.project.library.b_b_service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@AllArgsConstructor
@Controller
public class SearchingForBookController {
    private BookService bookService;

    @RequestMapping("/searchForABook")
    public ModelAndView showSearch() {
        BookPlusList bookPlusList = new BookPlusList();
        return new ModelAndView("searchForABook", "books", bookPlusList);
    }

    @RequestMapping("/searchForABookShowBooks")
    public ModelAndView searchForABook(Model model,
                                       @RequestParam("title") String title,
                                       @RequestParam("authorNS") String authorNS,
                                       @RequestParam("libraryNumber") String libraryNumber,
                                       @RequestParam("genre") String genre) {
        BookPlusList bookPlusList = new BookPlusList();
        model.addAttribute("books", bookPlusList);
        List<Book> bookList = bookService.find(title, authorNS, libraryNumber, genre);

        return new ModelAndView("searchForABook", "bookList", bookList);
    }
}
