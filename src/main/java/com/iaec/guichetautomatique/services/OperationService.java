package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.entities.Operation;
import org.springframework.data.domain.Page;

public interface OperationService {

    public Compte consulterCompte(int numeroCompte);
    public void verser(int numeroCompte, int montant);
    public void retirer(int numeroCompte, int montant);
    public Page<Operation> listOperation(int numeroCompte, int page, int size);
}
