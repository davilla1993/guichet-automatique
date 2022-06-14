package com.iaec.guichetautomatique.services.impl;

import com.iaec.guichetautomatique.entities.User;
import com.iaec.guichetautomatique.repository.UserRepository;
import com.iaec.guichetautomatique.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        try{
            return userRepository.findById(id).get();
        }catch(Exception ex) {
            throw new RuntimeException("Aucun utilisateur n'a été trouvé avec cet ID: " + id);
        }
    }

    @Override
    public User findByLogin(String login) {
        try{
            return userRepository.findUserByLogin(login);
        }catch(Exception ex){
            throw new RuntimeException("Aucun n'utilisateur n'a été trouvé avec ce login: " + login);
        }
    }

    @Override
    public List<User> findAllUsers() {
        try{
            return userRepository.findAll();
        }catch(Exception ex ){
            throw new RuntimeException("Aucun n'utilisateur n'a été trouvé dans la BDD");
        }
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
