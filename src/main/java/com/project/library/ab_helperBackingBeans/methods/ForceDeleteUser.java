package com.project.library.ab_helperBackingBeans.methods;

import com.project.library.b_b_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ForceDeleteUser {
    @Autowired
    private UserService userService;

    public static boolean thisUserHasBooks(int id1, int id2) {
        if (id1 == id2) {
            return true;
        }
        return false;
    }
}
