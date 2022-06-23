package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User getById(Integer id);

    List<User> getLastUser();

    User findByLogin(String login);

    List<User> findAllUsers();
    User updateAccount(User userInForm);

    void deleteUser(Integer id);

    void encodePassword(User user);
}
