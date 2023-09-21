package com.example.meireles.banker.domain.provider;

import com.example.meireles.banker.domain.model.AuthenticationModel;

public interface AuthenticationProvider {

    String authenticate(AuthenticationModel authenticationModel);

}
