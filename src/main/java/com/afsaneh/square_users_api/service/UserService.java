package com.afsaneh.square_users_api.service;

import com.afsaneh.square_users_api.entity.User;

import java.util.Optional;

public interface UserService {

    User createUser();

    Optional<User> findById(String id);

    void deleteById(String id);

    boolean isValidUser(String id);
}
