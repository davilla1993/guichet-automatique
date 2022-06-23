package com.iaec.guichetautomatique.services;

import com.iaec.guichetautomatique.entities.Role;

import java.util.List;

public interface RoleService {

    Role createRole(Role role);

    List<Role> getAllRoles();

    Role getRole(Integer id);

    void deleteRole(Integer id);


}
