package com.project.library.b_b_service;

import com.project.library.a_entity.Author;
import com.project.library.b_a_DAO.AuthorDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private AuthorDAO authorDAO;

    private static List<Integer> getDuplicates(List<Integer> list) {
        return list.stream()
                .filter(x -> numbersOf(list, x) > 1)
                .collect(Collectors.toList());
    }

    private static long numbersOf(List<Integer> list, int number) {
        return list.stream().filter(x -> x == number).count();
    }

    Optional<Author> findAuthorByNameAndSurname(String name, String surname) {
        return authorDAO.findAuthorByNameAndSurname(name, surname);
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
        List<String> authorsList = findRegex(authorsSN, "\\w+\\s\\w+");

        List<AuthorHelper> authorHelperList = new ArrayList<>();
        for (String s : authorsList) {
            String[] surnameName = s.split("\\s");
            authorHelperList.add(new AuthorHelper(surnameName[0], surnameName[1]));
        }
        return authorHelperList;
    }

    private List<String> findRegex(String authorsSN, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(authorsSN);
        List<String> authorsList = new ArrayList<>();
        while (matcher.find()) {
            authorsList.add(matcher.group(0));
        }
        return authorsList;
    }

    public List<Author> findByAuthorsName(String authorNS) {
        List<String> stringOfNames = findRegex(authorNS, "\\w+");
        List<Author> authorList = new ArrayList<>();

        for (String name : stringOfNames) {
            authorList.addAll(authorDAO.findAuthorsByName(name));
        }
        return authorList;
    }

    public List<Author> findByAuthorsSurname(String authorNS) {
        List<String> strimgOfSurnames = findRegex(authorNS, "\\w+");
        List<Author> authorList = new ArrayList<>();

        for (String surname : strimgOfSurnames) {
            authorList.addAll(authorDAO.findAuthorsBySurname(surname));
        }
        return authorList;
    }
}

@ToString
@AllArgsConstructor
@Data
class AuthorHelper {
    private String name;
    private String surname;
}
