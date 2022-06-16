package com.iaec.guichetautomatique.services.impl;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.repository.ClientRepository;
import com.iaec.guichetautomatique.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findById(int id) {
        try{
            return clientRepository.findById(id).get();
        } catch(Exception ex) {
            throw new RuntimeException("Aucun client n'a été trouvé avec cet ID: " + id);
        }
    }

    @Override
    public Client findByLogin(String login) {
        try{
            return clientRepository.findClientByLogin(login);
        } catch(Exception ex) {
            throw new RuntimeException("Aucun client n'a été trouvé avec ce login: " + login);
        }
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();

    }

    @Override
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
