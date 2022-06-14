package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.User;

import java.util.List;

public interface UserService {

    public User create(User user);

    public User findById(int id);

    public User findByLogin(String login);

    public List<User> findAllUsers();

    public void deleteUser(int id);

}
