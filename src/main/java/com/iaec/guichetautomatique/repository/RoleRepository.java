package com.iaec.guichetautomatique.repository;

import com.iaec.guichetautomatique.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Long countById(Integer id);
}
