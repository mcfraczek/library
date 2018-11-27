package com.project.library.ab_helperBackingBeans.methods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateChecking {
    public static boolean thereIsAFine(String date) {
        LocalDate timeBorrowed = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(date));
        LocalDate nowMinus30 = LocalDate.now().minusDays(30);
        if (nowMinus30.isAfter(timeBorrowed)) {
            return true;
        }
        return false;
    }
}
