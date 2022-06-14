package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.entities.User;

import java.util.List;

public interface ClientService {

    public Client create(Client client);

    public Client findById(int id);

    public Client findByLogin(String login);

    public List<Client> findAllClients();

    public void deleteClient(int id);
}
