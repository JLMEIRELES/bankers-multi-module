package com.example.meireles.banker.domain.service.impl;

import com.example.meireles.banker.domain.model.AuthenticationModel;
import com.example.meireles.banker.domain.provider.AuthenticationProvider;
import com.example.meireles.banker.domain.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public String authenticate(AuthenticationModel authenticationModel) {
        return authenticationProvider.authenticate(authenticationModel);
    }
}
