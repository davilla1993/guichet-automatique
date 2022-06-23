package com.iaec.guichetautomatique.repository;

import com.iaec.guichetautomatique.entities.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {

    @Query("select c from Compte c order by c.dateCreation desc")
    Page<Compte> listCompteByPage(Pageable pageable);

    Optional<Compte> findCompteByNumeroCompte(Integer numCompte);

    List<Compte> findCompteByUser(Integer id);

    List<Compte> findComptesByUser(Integer id);

    List<Compte> findByUserId(Integer id);
}
