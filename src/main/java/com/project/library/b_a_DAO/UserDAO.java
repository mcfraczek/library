package com.project.library.b_a_DAO;

import com.project.library.a_entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO extends CrudRepository<User, Integer> {
    List<User> findUserByNameAndSurnameOrderBySurname(String name, String surname);

    List<User> findUserByNameOrderBySurname(String name);

    List<User> findUserBySurnameOrderBySurname(String surname);

}
