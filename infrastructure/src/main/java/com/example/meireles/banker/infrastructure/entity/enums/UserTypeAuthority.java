package com.example.meireles.banker.infrastructure.entity.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

public enum UserTypeAuthority implements GrantedAuthority {

    ADM,
    CUSTOMER,
    NOT_MAPPED;

    public static UserTypeAuthority from(String name){
        return Arrays.stream(UserTypeAuthority.values())
                .filter(type -> type.name().equals(name))
                .findFirst()
                .orElse(NOT_MAPPED);
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
