package org.sid.jwtspringsec.dao;

import org.sid.jwtspringsec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
}
