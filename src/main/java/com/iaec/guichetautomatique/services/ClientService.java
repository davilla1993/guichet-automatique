package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.Client;

import java.util.List;

public interface ClientService {

    public Client create(Client client);

    public Client getById(Integer id);

    public List<Client> getLastClient();

    public Client findByLogin(String login);

    public List<Client> findAllClients();

    public void deleteClient(Integer id);
}
