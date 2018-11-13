package com.project.library.ab_helperBackingBeans.book;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import com.project.library.ab_helperBackingBeans.ListHelper;
import lombok.Data;

import java.util.Iterator;
import java.util.List;

@Data
public class BookPlusList implements ListHelper {
    private Book book;

    @Override
    public List<String> listFromDirectFile() {
        return list("book", "bookGenres.txt");
    }

    public void eraseNullAuthors() {
        Iterator<Author> iterator = book.getAuthorList().iterator();
        while (iterator.hasNext()) {
            Author author = iterator.next();
            if (author.getName() == null || author.getSurname() == null) {
                iterator.remove();
            }
        }
    }
}
