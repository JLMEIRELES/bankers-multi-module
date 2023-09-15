package com.example.meireles.banker.application.utils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * A configuration class to manage {@link Random} class
 */
@Configuration
public class RandomConfig {

    /**
     * A construct that creates and injects a dependency of {@link Random}
     * @return the created {@link Random}
     */
    @Bean
    public Random random(){
        return new Random();
    }

}
