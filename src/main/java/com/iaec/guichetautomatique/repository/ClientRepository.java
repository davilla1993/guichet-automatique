package com.iaec.guichetautomatique.repository;
import com.iaec.guichetautomatique.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByLogin(String login);

    public Long countById(Integer id);
}
