package com.iaec.guichetautomatique.repository;
import com.iaec.guichetautomatique.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
    @Query("select u from User u where u.login=:login")
    User getUserByLogin(@Param("login") String login);
    User getByLogin(String login);
    public Long countById(Integer id);
    @Query(value = "select u from User u order by u.id DESC")
    List<User> findLastClient();
}
