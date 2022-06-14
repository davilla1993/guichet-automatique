package com.iaec.guichetautomatique.services.impl;

import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.entities.Operation;
import com.iaec.guichetautomatique.entities.Retrait;
import com.iaec.guichetautomatique.entities.Versement;
import com.iaec.guichetautomatique.repository.CompteRepository;
import com.iaec.guichetautomatique.repository.OperationRepository;
import com.iaec.guichetautomatique.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    private CompteRepository compteRepository;
    private OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(CompteRepository compteRepository, OperationRepository operationRepository) {
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public Compte consulterCompte(int numeroCompte) {

        try {
            return compteRepository.findById(numeroCompte).get();
        } catch (Exception e) {
            throw new RuntimeException("Aucun compte n'a été trouvé avec ce numéro : " + numeroCompte);

        }

    }

    @Override
    public void verser(int numeroCompte, double montant) {

        Compte compte = consulterCompte(numeroCompte);
        Versement versement = new Versement(new Date(), montant, compte);
        operationRepository.save(versement);
        compte.setSolde(compte.getSolde() + montant);

        compteRepository.save(compte);
    }

    @Override
    public void retirer(int numeroCompte, double montant) {
        Compte compte = consulterCompte(numeroCompte);

        if(compte.getSolde() > montant ) {
            Retrait retrait = new Retrait(new Date(), montant, compte);
            operationRepository.save(retrait);
            compte.setSolde(compte.getSolde() - montant);
            compteRepository.save(compte);
        } else {
            throw new RuntimeException("Le solde du compte est insuffisant");
        }
    }

    @Override
    public Page<Operation> listOperation(int numeroCompte, int page, int size) {

        return operationRepository.listOperation(numeroCompte, PageRequest.of(page, size));
    }
}
