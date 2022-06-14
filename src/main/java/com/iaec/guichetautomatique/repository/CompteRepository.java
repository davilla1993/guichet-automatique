package com.iaec.guichetautomatique.repository;

import com.iaec.guichetautomatique.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
}
