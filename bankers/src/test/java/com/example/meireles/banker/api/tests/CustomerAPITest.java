package com.example.meireles.banker.api.tests;

import com.example.meireles.banker.application.dto.request.CustomerRequest;
import com.example.meireles.banker.application.dto.response.CustomerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class CustomerAPITest extends BaseAPITest {

    @InjectMocks
    private ObjectMapper objectMapper;

    private final String jsonPath = "src/test/resources/json/";

    public CustomerAPITest() {
        super(new ObjectMapper().registerModule(new JavaTimeModule()));
    }

    @BeforeEach
    void init(){
        basePath = "http://localhost:%d/v1/customer";
    }

    @Test
    void shouldCreateCustomer() throws IOException {
        CustomerRequest customerRequest =
                toEntity(jsonPath + "customer.json", CustomerRequest.class);

        var response = restTemplate.
                postForEntity(getPath(), customerRequest, CustomerResponse.class);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId())
        );
    }
}
