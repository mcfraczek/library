package com.project.library.c_controllers;

import com.project.library.a_entity.Book;
import com.project.library.ab_helperBackingBeans.book.BookPlusList;
import com.project.library.b_b_service.QuickSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class QuickSearchController {
    @Autowired
    private QuickSearchService quickSearchService;

    @RequestMapping("/quickSearch")
    public ModelAndView quickSearch(@RequestParam("searched") String searched, Model model) {
        List<Book> bookList = quickSearchService.search(searched);
        model.addAttribute("bookList", bookList);
        BookPlusList bookPlusList = new BookPlusList();
        return new ModelAndView("searchForABook", "books", bookPlusList);
    }
}
