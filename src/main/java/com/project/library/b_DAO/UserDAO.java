package com.project.library.b_DAO;

import com.project.library.a_entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {
}
