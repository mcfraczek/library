package com.project.library.b_a_service;

import com.project.library.a_entity.Author;
import com.project.library.b_DAO.AuthorDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private AuthorDAO authorDAO;

    public static List<Integer> getDuplicates(List<Integer> list) {
        return list.stream()
                .filter(x -> numbersOf(list, x) > 1)
                .collect(Collectors.toList());
    }

    public static long numbersOf(List<Integer> list, int number) {
        return list.stream().filter(x -> x == number).count();
    }

    public List<Author> findByAuthor(String authorsNS) {

        List<AuthorHelper> authorHelperList = fromStringToSurnameName(authorsNS);
        List<Author> authorList = new ArrayList<>();

        for (AuthorHelper authorHelper : authorHelperList) {
            List<Author> helper = entityManager.createQuery
                    ("from Author a where a.name=" + "'" + authorHelper.getName() + "'" +
                            " and a.surname=" + "'" + authorHelper.getSurname() + "'", Author.class)
                    .getResultList();
            authorList.addAll(helper);
        }

        return authorList;
    }

    private List<AuthorHelper> fromStringToSurnameName(String authorsSN) {
        Pattern pattern = Pattern.compile("\\w+\\s\\w+");
        Matcher matcher = pattern.matcher(authorsSN);
        List<String> authorsList = new ArrayList<>();
        while (matcher.find()) {
            authorsList.add(matcher.group(0));
        }

        List<AuthorHelper> authorHelperList = new ArrayList<>();
        for (String s : authorsList) {
            String[] surnameName = s.split("\\s");
            authorHelperList.add(new AuthorHelper(surnameName[0], surnameName[1]));
        }
        return authorHelperList;
    }
}

@ToString
@AllArgsConstructor
@Data
class AuthorHelper {
    private String name;
    private String surname;
}
