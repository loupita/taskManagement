package org.sid.jwtspringsec.service.impl;

import org.sid.jwtspringsec.dao.RoleRepository;
import org.sid.jwtspringsec.dao.UserRepository;
import org.sid.jwtspringsec.entities.AppRole;
import org.sid.jwtspringsec.entities.AppUser;
import org.sid.jwtspringsec.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public AppUser saveUser(AppUser user) {
        String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        //System.out.println("le password est : "+ hashPW);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername(username);
        AppRole role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
