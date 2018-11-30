package com.project.library.b_b_service;

import com.project.library.a_entity.Type;
import com.project.library.b_a_DAO.TypeDEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService {
    @Autowired
    private TypeDEO typeDEO;

    public Optional<Type> findTypeByType(String type) {
        return typeDEO.findTypeByTypeIgnoreCase(type);
    }
}