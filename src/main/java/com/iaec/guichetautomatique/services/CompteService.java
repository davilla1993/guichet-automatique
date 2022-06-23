package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.Compte;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompteService {

    Compte create(Compte compte);

    List<Compte> findAll();

    Page<Compte> listCompteByPage(int page, int size);

    List<Compte> getComptesByUser(Integer id);
     Compte findById(int id);
     Compte getCompteByNumeroCompte(Integer numCompte);

     void delete(int numeroCompte);


}
