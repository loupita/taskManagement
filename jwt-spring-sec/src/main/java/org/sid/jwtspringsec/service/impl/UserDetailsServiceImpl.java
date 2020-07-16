package org.sid.jwtspringsec.service.impl;

import org.sid.jwtspringsec.entities.AppUser;
import org.sid.jwtspringsec.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = accountService.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        //Les roles d'un user dans Spring sont de types GrantedAuthority
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(r ->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        User a = new User(user.getUsername(), user.getPassword(), authorities);
        System.out.println("L'utilisateur est " + a.getUsername() + " le mdp est " + a.getPassword() + " les auth " + a.getAuthorities());
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
