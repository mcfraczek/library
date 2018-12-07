package com.project.library.ab_helperBackingBeans;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public interface ListHelper {
    default List<String> list(String folder, String name) {
        List<String> list = null;
        Path path = Paths.get("src", "main", "java", "com", "project", "library", "ab_helperBackingBeans");
        Path folderName = Paths.get(folder, name);

        try {
            list = Files.readAllLines(path.resolve(folderName)).stream().sorted().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    List<String> listFromDirectFile();
}
