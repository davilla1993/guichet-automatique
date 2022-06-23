package com.iaec.guichetautomatique.services.impl;

import com.iaec.guichetautomatique.entities.Role;
import com.iaec.guichetautomatique.repository.RoleRepository;
import com.iaec.guichetautomatique.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Integer id) {
        Optional<Role> result = roleRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new RuntimeException("Aucun role trouvé avec cet ID : " + id);
    }

    @Override
    public void deleteRole(Integer id) {
        Long count = roleRepository.countById(id);
        if(count == null || count == 0){
            throw new RuntimeException("Aucun role n'a été trouvé avec ce ID " + id);
        }
        roleRepository.deleteById(id);

    }
}
