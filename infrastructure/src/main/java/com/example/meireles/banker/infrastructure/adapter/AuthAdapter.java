package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.model.AuthenticationModel;
import com.example.meireles.banker.domain.provider.AuthenticationProvider;
import com.example.meireles.banker.infrastructure.config.authentication.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthAdapter implements AuthenticationProvider {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public String authenticate(AuthenticationModel authenticationModel) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(authenticationModel.getLogin(), authenticationModel.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authenticationModel.getLogin());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }


}
