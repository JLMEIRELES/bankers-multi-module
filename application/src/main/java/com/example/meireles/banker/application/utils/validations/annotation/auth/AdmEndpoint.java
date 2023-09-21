package com.example.meireles.banker.application.utils.validations.annotation.auth;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@PreAuthorize("authentication.principal.userType.name() == 'ADM'")
public @interface AdmEndpoint {
}
