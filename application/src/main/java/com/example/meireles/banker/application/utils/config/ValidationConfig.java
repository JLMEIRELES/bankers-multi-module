package com.example.meireles.banker.application.utils.config;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    @Bean
    public CPFValidator cpfValidator() {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        return cpfValidator;
    }

    @Bean
    public CNPJValidator cnpjValidator() {
        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);
        return cnpjValidator;
    }

}
