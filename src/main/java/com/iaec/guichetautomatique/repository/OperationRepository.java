package com.iaec.guichetautomatique.repository;

import com.iaec.guichetautomatique.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {

    @Query("select o from Operation o where o.compte.numeroCompte=:numCompte order by o.dateOperation ASC")
    public Page<Operation> listOperation(@Param("numCompte") int numeroCompte, Pageable pageable);
}
