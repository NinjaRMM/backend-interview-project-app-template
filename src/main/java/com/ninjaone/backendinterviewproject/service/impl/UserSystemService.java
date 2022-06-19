package com.ninjaone.backendinterviewproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.UserSystemRepository;
import com.ninjaone.backendinterviewproject.exception.GenericException;
import com.ninjaone.backendinterviewproject.model.UserSystem;
import com.ninjaone.backendinterviewproject.service.api.UserSystemServiceInterface;

@Service
public class UserSystemService extends AbstractServiceImpl<UserSystem, String, UserSystemRepository>
        implements UserSystemServiceInterface {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserSystem create(UserSystem entity) throws GenericException {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.create(entity);
    }
}
