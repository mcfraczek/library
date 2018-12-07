package com.project.library.ab_helperBackingBeans.methods;

import org.springframework.stereotype.Service;


@Service
public class ForceDeleteUser {

    public static boolean hasError(int id1, int id2) {
        if (id1 == id2) {
            return true;
        }
        return false;
    }
}
