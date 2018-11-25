package com.project.library.ab_helperBackingBeans.methods;

import com.project.library.a_entity.Author;

import java.util.List;
import java.util.stream.Collectors;

public class Concatinating {
    public static String concatinate(List<Author> authorList) {
        return authorList.stream().map(author -> author.getName() + " " + author.getSurname())
                .collect(Collectors.joining(", "));
    }
}
