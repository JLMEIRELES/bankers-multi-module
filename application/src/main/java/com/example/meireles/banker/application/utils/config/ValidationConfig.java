package com.example.meireles.banker.application.utils.config;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A configuration class to manage CPF and CNPJ validators
 */
@Configuration
public class ValidationConfig {

    /**
     * A construct that creates and injects a dependency of {@link CPFValidator}
     * @return the created {@link CPFValidator}
     */
    @Bean
    public CPFValidator cpfValidator() {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        return cpfValidator;
    }

    /**
     * A construct that creates and injects a dependency of {@link CNPJValidator}
     * @return the created {@link CNPJValidator}
     */
    @Bean
    public CNPJValidator cnpjValidator() {
        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);
        return cnpjValidator;
    }

}
