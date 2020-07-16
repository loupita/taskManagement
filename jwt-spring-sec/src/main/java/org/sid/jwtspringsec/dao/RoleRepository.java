package org.sid.jwtspringsec.dao;

import org.sid.jwtspringsec.entities.AppRole;
import org.sid.jwtspringsec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
    public AppRole findByRoleName(String roleName);
}
