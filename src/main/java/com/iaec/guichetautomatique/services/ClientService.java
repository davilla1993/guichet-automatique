package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {

    public Client create(Client client);

    public Client getById(Integer id);

    public Client findByLogin(String login);

    public List<Client> findAllClients();

    public void deleteClient(Integer id);
}
