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
import java.util.Optional;

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
        client.setLogin(client.getNom());
        client.setPassword(client.getTelephone());

        return clientRepository.save(client);
    }

    @Override
    public Client getById(Integer id) {
       Optional<Client> result = clientRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new RuntimeException("Aucun client n'a été trouvé avec cet ID: " + id);

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
    public void deleteClient(Integer id) {
        Long count = clientRepository.countById(id);
        if(count == null || count == 0){
            throw new RuntimeException("Aucun client n'a été trouvé avec ce ID: " + id);
        }
        clientRepository.deleteById(id);
    }
}
