package com.project.library.b_a_DAO;

import com.project.library.a_entity.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeDEO extends CrudRepository<Type, Integer> {
    Optional<Type> findTypeByType(String type);
}
