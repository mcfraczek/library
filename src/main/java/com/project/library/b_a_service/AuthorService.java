package com.project.library.b_a_service;

import com.project.library.a_entity.Author;
import com.project.library.a_entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private EntityManager entityManager;

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

        if (authorHelperList.size() > 1) { /* jeśli jest wiecej niż jeden autor*/
            authorList = eraseAuthorsWhoHaventWroteThisBook(authorList);
        }
        return authorList;
    }

    private List<Author> eraseAuthorsWhoHaventWroteThisBook(List<Author> authorList) {
         /*jeśli lista książek autora nie równa się ządnej liście książek żadenego autora.
                Usuń tego autora */
        Map<List<Book>, Integer> map = new HashMap<>();

        for (Author author : authorList) {
            if (map.containsKey(author.getBookList())) {
                Integer count = map.get(author.getBookList());
                map.put(author.getBookList(), count + 1);
            } else {
                map.put(author.getBookList(), 1);
            }
        }

        int max = 0;
        for (Map.Entry<List<Book>, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        List<Book> bookList = null;
        for (Map.Entry<List<Book>, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                bookList = entry.getKey();
                System.out.println(bookList);
            }
        }
        final List<Book> bookList1 = bookList;
        List<Author> newAuthorList = authorList.stream()
                .filter(author -> author.getBookList().equals(bookList1))
                .collect(Collectors.toList());

        return newAuthorList;
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
