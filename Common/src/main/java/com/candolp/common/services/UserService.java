package com.candolp.common.services;

import com.candolp.common.generic.GenericService;
import com.candolp.common.models.User;

public interface UserService extends GenericService<User> {
    boolean authenticate(String username, String password);

    User findByEmail(String email);

    User findByName(String name);
    User getDefaultUserDetails(String userID);
    User updatePassword(User user);

}
