package com.project.library.ab_helperBackingBeans.book;

import com.project.library.a_entity.Book;
import com.project.library.ab_helperBackingBeans.ListHelper;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class BookPlusList implements ListHelper {
    @Valid
    private Book book;

    @Override
    public List<String> listFromDirectFile() {
        return list("book", "bookGenres.txt");
    }
}
