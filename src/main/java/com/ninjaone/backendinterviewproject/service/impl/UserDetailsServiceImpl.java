package com.ninjaone.backendinterviewproject.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ninjaone.backendinterviewproject.model.UserSystem;
import com.ninjaone.backendinterviewproject.service.api.UserSystemServiceInterface;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserSystemServiceInterface userSystemServiceInterface;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserSystem userSystem = userSystemServiceInterface.findById(username);
            return new User(userSystem.getUsername(), userSystem.getPassword(), userSystem.getAuthorities());
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("User " + username + " not found.", e);
        }
    }

}
