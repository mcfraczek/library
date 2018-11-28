package com.project.library.ab_helperBackingBeans.methods;

import com.project.library.a_entity.Book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface DateChecking {
    static boolean thereIsAFine(String date) {
        LocalDate timeBorrowed = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(date));
        LocalDate nowMinus30 = LocalDate.now().minusDays(1);
        if (nowMinus30.isAfter(timeBorrowed)) {
            return true;
        }
        return false;
    }

    static boolean thereIsAFine(List<Book> bookList) {
        for (Book book : bookList) {
            LocalDate timeBorrowed = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(book.getDate()));
            LocalDate nowMinus30 = LocalDate.now().minusDays(1);
            if (nowMinus30.isAfter(timeBorrowed)) {
                return true;
            }
        }
        return false;
    }
}
/*  static boolean thereIsAFine(List<Book> bookList) {
        for (Book book : bookList) {
            LocalDate timeBorrowed = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(book.getDate()));
            LocalDate nowMinus30 = LocalDate.now().minusDays(30);
            if (nowMinus30.equals(timeBorrowed)) {
                return true;
            }
        }
        return false;
    }*/