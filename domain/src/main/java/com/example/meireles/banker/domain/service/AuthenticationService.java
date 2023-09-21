package com.example.meireles.banker.domain.service;

import com.example.meireles.banker.domain.model.AuthenticationModel;

public interface AuthenticationService {
    String authenticate(AuthenticationModel authenticationModel);
}
