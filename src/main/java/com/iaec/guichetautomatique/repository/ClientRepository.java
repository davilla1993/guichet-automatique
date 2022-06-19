package com.iaec.guichetautomatique.repository;
import com.iaec.guichetautomatique.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByLogin(String login);
    public Long countById(Integer id);
    @Query(value = "select * from Client order by Client.id DESC LIMIT 4", nativeQuery = true)
    List<Client> findLastClient();
}
