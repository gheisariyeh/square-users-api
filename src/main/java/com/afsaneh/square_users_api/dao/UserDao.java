package com.afsaneh.square_users_api.dao;

import com.afsaneh.square_users_api.entity.User;

import java.util.Optional;

public interface UserDao {

    User save(User user);

    Optional<User> findById(String id);

    void deleteById(String id);

    boolean existsById(String id);
}
