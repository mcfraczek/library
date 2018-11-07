package com.project.library.ab_helperBackingBeans;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public interface ListHelper {
    default List<String> list(String folder, String name) {
        List<String> list = new ArrayList<>();
        Path path = Paths.get("src", "main", "java", "com", "project", "library", "ab_helperBackingBeans");
        Path folderName = Paths.get(folder, name);

        try (InputStream inputStream = Files.newInputStream(path.resolve(folderName));
             Reader reader = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader(reader)) {
            br.lines().forEach(s -> {
                list.add(s.trim());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    List<String> listFromDirectFile();
}
