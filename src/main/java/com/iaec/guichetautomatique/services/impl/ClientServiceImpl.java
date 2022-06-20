package com.iaec.guichetautomatique.services.impl;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.repository.ClientRepository;
import com.iaec.guichetautomatique.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

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
        client.setLogin(generatLogin());
        client.setPassword(generatePassword());
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
    public List<Client> getLastClient() {
        List<Client> lastClient = clientRepository.findLastClient();

        return lastClient;

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

    @Transient
    private String generatePassword() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return String.valueOf(sb).toLowerCase();
    }

    @Transient
    private String generatLogin(){
        Long numCompte = (long) Math.floor(Math.random() * 9_00L) + 1_00L;
        String login = "user".concat(String.valueOf(numCompte));

        return login;
    }
}
