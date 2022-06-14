package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.Compte;

import java.util.List;

public interface CompteService {

    public Compte create(Compte compte);

    public List<Compte> findAll();

    public Compte findById(int numeroCompte);

    public void delete(int numeroCompte);


}
