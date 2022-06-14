package com.iaec.guichetautomatique.repository;

import com.iaec.guichetautomatique.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByLogin(String login);
}
