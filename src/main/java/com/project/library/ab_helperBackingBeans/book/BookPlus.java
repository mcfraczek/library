package com.project.library.ab_helperBackingBeans.book;

import com.project.library.a_entity.Book;
import com.project.library.ab_helperBackingBeans.ListHelper;
import lombok.Data;

import java.util.List;

@Data
public class BookPlus implements ListHelper {
    private Book book;

    public List<String> genresList() {
        return list("book", "bookGenres.txt");
    }
}
