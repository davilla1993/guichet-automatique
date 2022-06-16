package com.iaec.guichetautomatique.repository;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findClientByLogin(String login);

    @Query("select c from Client c order by c.id ASC")
    public Page<Client> findAllClients(Pageable pageable);
}
