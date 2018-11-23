package com.project.library.ab_helperBackingBeans.book;

import com.project.library.a_entity.Book;
import com.project.library.ab_helperBackingBeans.ListHelper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class BooksPlusList implements ListHelper {
    private List<Book> bookList;

    @Override
    public List<String> listFromDirectFile() {
        return list("book", "bookGenres.txt");
    }
}
