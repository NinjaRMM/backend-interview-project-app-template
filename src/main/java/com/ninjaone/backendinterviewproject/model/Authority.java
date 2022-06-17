package com.ninjaone.backendinterviewproject.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority{

    @Id
    @Column(name = "authority_name",
    updatable = false)
    private String authorityName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorities")
    Collection<UserSystem> users = new ArrayList<>();

    public Authority(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.authorityName;
    }
}
