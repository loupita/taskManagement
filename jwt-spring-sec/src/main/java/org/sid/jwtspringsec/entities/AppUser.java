package org.sid.jwtspringsec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class AppUser {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    @Column()
    private String password;
    @ManyToMany(fetch = FetchType.EAGER) //Chaque fois que la classe AppUser est chagée, la liste de ces roles el'est aussi.
    private Collection<AppRole> roles = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(Long id, String username, String password, Collection<AppRole> roles){
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //@JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonSetter
    public Collection<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<AppRole> roles) {
        this.roles = roles;
    }
}
