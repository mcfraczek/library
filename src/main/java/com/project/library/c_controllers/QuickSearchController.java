package com.project.library.c_controllers;

import com.project.library.a_entity.Book;
import com.project.library.ab_helperBackingBeans.book.BookPlusList;
import com.project.library.b_b_service.QuickSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuickSearchController {
    @Autowired
    private QuickSearchService quickSearchService;

    @RequestMapping("/quickSearch")
    public String quickSearch(@RequestParam("searched") String searched, Model model) {
        if (searched.isEmpty()) {
            return "index";
        }
        List<Book> bookList = quickSearchService.search(searched);
        model.addAttribute("bookList", bookList);
        BookPlusList bookPlusList = new BookPlusList();
        model.addAttribute("books", bookPlusList);
        return "searchForABook";
    }
}
