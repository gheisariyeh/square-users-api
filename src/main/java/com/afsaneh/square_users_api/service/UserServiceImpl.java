package com.afsaneh.square_users_api.service;

import com.afsaneh.square_users_api.dao.UserDao;
import com.afsaneh.square_users_api.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements  UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(String username, String password) {
        String id = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(id, username,encodedPassword, "ROLE_USER");
        return userDao.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    @Override
    public boolean isValidUser(String id) {
        return userDao.existsById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
