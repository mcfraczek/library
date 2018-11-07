package com.project.library.ab_helperBackingBeans.user;

import com.project.library.a_entity.User;
import com.project.library.ab_helperBackingBeans.ListHelper;
import lombok.Data;

import java.util.List;

@Data
public class UserPlusList implements ListHelper {
    private User user;

    @Override
    public List<String> listFromDirectFile() {
        return list("user", "counties.txt");
    }
}
