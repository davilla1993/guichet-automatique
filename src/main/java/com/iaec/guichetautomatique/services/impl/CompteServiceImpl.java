package com.iaec.guichetautomatique.services.impl;

import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.repository.CompteRepository;
import com.iaec.guichetautomatique.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private CompteRepository compteRepository;

    @Autowired
    public CompteServiceImpl(CompteRepository compteRepository) {

        this.compteRepository = compteRepository;
    }

    @Override
    public Compte create(Compte compte) {
        compte.setNumeroCompte(generateNumCompte());
        compte.setDateCreation(new Date());

        return compteRepository.save(compte);
    }

    @Override
    public List<Compte> findAll() {
        return compteRepository.findAll();
    }

    @Override
    public Page<Compte> listCompteByPage(int page, int size) {
        return compteRepository.listCompteByPage(PageRequest.of(page, size));
    }

    @Override
    public Compte findById(int numeroCompte) {
        Compte compte = compteRepository.findById(numeroCompte).get();
        if(compte == null){
            throw new RuntimeException("Aucun compte n'existe avec ce numero");
        }
        return compte;
    }

    @Override
    public void delete(int numeroCompte) {
        compteRepository.deleteById(numeroCompte);
    }

    @Transient
    private int generateNumCompte(){
//        Long numCompte = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        Random r = new Random(System.currentTimeMillis());
        int numCompte =  Math.abs(1000000000 + r.nextInt(2000000000));

        return numCompte;
    }
}
