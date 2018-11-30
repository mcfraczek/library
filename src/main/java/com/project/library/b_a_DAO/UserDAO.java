package com.project.library.b_a_DAO;

import com.project.library.a_entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO extends CrudRepository<User, Integer> {
    List<User> findUserByNameAndSurnameIgnoreCaseOrderBySurname(String name, String surname);

    List<User> findUserByNameIgnoreCaseOrderBySurname(String name);

    List<User> findUserBySurnameIgnoreCaseOrderBySurname(String surname);

}
