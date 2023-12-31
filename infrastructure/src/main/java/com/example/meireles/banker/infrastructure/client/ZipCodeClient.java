package com.example.meireles.banker.infrastructure.client;

import com.example.meireles.banker.infrastructure.client.dto.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Viacep client to get an address by its zip code
 */
@Component
public class ZipCodeClient {

    private static final String BASE_URL = "https://viacep.com.br/ws/%s/json";

    @Autowired
    private WebClient.Builder webClientBuilder;

    /**
     * Method that do a get request to Viacep API and gets the address by its zip code
     *
     * @param zipCode the address zip code
     * @return an instance of {@link Endereco} that represents the address
     */
    public Endereco getAddress(String zipCode){
        return webClientBuilder.build()
                .get()
                .uri(String.format(BASE_URL, zipCode))
                .retrieve()
                .bodyToMono(Endereco.class)
                .block();
    }

}
