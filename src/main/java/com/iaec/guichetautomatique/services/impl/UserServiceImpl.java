package com.iaec.guichetautomatique.services.impl;

import com.iaec.guichetautomatique.entities.User;
import com.iaec.guichetautomatique.repository.UserRepository;
import com.iaec.guichetautomatique.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        boolean isUpdatingUser = (user.getId() != null);

        if(isUpdatingUser) {
            User existingUser = userRepository.findById(user.getId()).get();

            if(user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }
        } else {

            encodePassword(user);
        }

        return userRepository.save(user);

    }

    @Override
    public User getById(Integer id) {
       Optional<User> result = userRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new RuntimeException("Aucun user n'a été trouvé avec cet ID: " + id);

    }

    @Override
    public List<User> getLastUser() {
        List<User> lastUsers = userRepository.findLastClient();

        return lastUsers;

    }

    @Override
    public User findByLogin(String login) {
        try{
            return userRepository.findUserByLogin(login);
        } catch(Exception ex) {
            throw new RuntimeException("Aucun user n'a été trouvé avec ce login: " + login);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();

    }

    @Override
    public User updateAccount(User userInForm) {

        User userInDB = userRepository.findById(userInForm.getId()).get();
        if(!userInForm.getPassword().isEmpty()){
            userInDB.setPassword(userInForm.getPassword());
            encodePassword(userInDB);
        }

        userInDB.setNom(userInForm.getNom());
        userInDB.setPrenom(userInForm.getPrenom());
        userInDB.setTelephone(userInForm.getTelephone());
        userInDB.setLogin(userInForm.getLogin());

        return userRepository.save(userInDB);
    }

    @Override
    public void deleteUser(Integer id) {
        Long count = userRepository.countById(id);
        if(count == null || count == 0){
            throw new RuntimeException("Aucun user n'a été trouvé avec ce ID: " + id);
        }
        userRepository.deleteById(id);
    }
    @Override
    public void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    @Transient
    private String generatePassword() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return String.valueOf(sb).toLowerCase();
    }

    @Transient
    private String generatLogin(){
        Long numCompte = (long) Math.floor(Math.random() * 9_00L) + 1_00L;
        String login = "user".concat(String.valueOf(numCompte));

        return login;
    }
}
