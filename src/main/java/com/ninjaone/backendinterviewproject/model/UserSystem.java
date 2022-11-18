package com.ninjaone.backendinterviewproject.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_system")
@AllArgsConstructor
@NoArgsConstructor
public class UserSystem implements AbstractEntity<String>  {
    @Id
    @Column(name = "username", updatable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority_name", referencedColumnName = "authority_name"))
    private Collection<Authority> authorities = new ArrayList<>();

    @Override
    public String getId() {
        return username;
    }

    public UserSystem(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
