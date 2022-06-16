package com.iaec.guichetautomatique.repository;

import com.iaec.guichetautomatique.entities.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

    @Query("select c from Compte c order by c.dateCreation ASC")
    public Page<Compte> listCompteByPage(Pageable pageable);
}
