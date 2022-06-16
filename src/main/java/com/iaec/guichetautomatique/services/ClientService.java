package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {

    public Client create(Client client);

    public Client findById(int id);

    public Client findByLogin(String login);

    public Page<Client> findAllClients(int page, int size);

    public void deleteClient(int id);
}
