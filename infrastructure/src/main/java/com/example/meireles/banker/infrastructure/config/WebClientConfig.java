package com.example.meireles.banker.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Class that configure the {@link WebClient}
 */
@Configuration
public class WebClientConfig {

    /**
     * A construct that creates and injects a dependency of {@link WebClient.Builder}
     * @return the created {@link WebClient.Builder}
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
