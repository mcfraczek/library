package com.project.library.ab_helperBackingBeans.book;

import com.project.library.a_entity.Book;
import lombok.Data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookPlus {
    private Book book;

    public static List<String> genresList() {
        List<String> genresList = new ArrayList<>();
        Path path = Paths.get("src", "main", "java", "com", "project", "library", "ab_helperBackingBeans", "book", "bookGenres.txt");
        try (InputStream inputStream = Files.newInputStream(path);
             Reader reader = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader(reader)) {
            br.lines().forEach(s -> {
                genresList.add(s.trim());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return genresList;
    }
}
