package com.afsaneh.square_users_api.service;

import com.afsaneh.square_users_api.dao.UserDao;
import com.afsaneh.square_users_api.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements  UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User createUser() {
        String id = UUID.randomUUID().toString();
        User user = new User(id);
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
}
