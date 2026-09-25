package com.afsaneh.square_users_api.security;

import com.afsaneh.square_users_api.dao.UserDao;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {

    private final UserDao userDao;

    public UserSecurity(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean isOwner(String userId, String username) {
        return userDao.findById(userId)
                .map(user -> user.getUsername().equals(username))
                .orElse(false);
    }
}