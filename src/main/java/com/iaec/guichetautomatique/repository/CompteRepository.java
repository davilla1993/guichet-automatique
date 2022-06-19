package com.iaec.guichetautomatique.repository;

import com.iaec.guichetautomatique.entities.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

    @Query("select c from Compte c order by c.dateCreation desc")
    public Page<Compte> listCompteByPage(Pageable pageable);

    public Optional<Compte> findCompteByNumeroCompte(Integer numCompte);
}
