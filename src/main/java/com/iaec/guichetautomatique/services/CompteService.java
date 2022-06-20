package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.Compte;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompteService {

    public Compte create(Compte compte);

    public List<Compte> findAll();

    public Page<Compte> listCompteByPage(int page, int size);

    public List<Compte> getCompteByClient(Integer id);

    public Compte findById(int id);

    public Compte getCompteByNumeroCompte(Integer numCompte);

    public void delete(int numeroCompte);


}
