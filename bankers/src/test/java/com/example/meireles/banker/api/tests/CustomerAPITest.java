package com.example.meireles.banker.api.tests;

import com.example.meireles.banker.application.controller.handler.ErrorResponse;
import com.example.meireles.banker.application.dto.request.CustomerRequest;
import com.example.meireles.banker.application.dto.response.CustomerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
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

    /**
     * Checks if the customer will be created
     *
     * @throws IOException if there's an error on json parse
     */
    @Test
    @ExpectedDataSet("/started/customer.yml")
    void shouldCreateCustomer() throws IOException {
        CustomerRequest customerRequest =
                toEntity(jsonPath + "customer.json", CustomerRequest.class);

        var response = restTemplate.
                postForEntity(getPath(), customerRequest, CustomerResponse.class);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId()),
                () -> Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode())
        );
    }

    /**
     * Checks if email and document are been correctly validated
     *
     * @throws IOException if there's an error on json parse
     */
    @Test
    void shouldNotCreateCustomer() throws IOException {
        CustomerRequest customerRequest = toEntity(jsonPath + "invalid-customer.json", CustomerRequest.class);

        var response = restTemplate.
                postForEntity(getPath(), customerRequest, ErrorResponse.class);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode()),
                () -> Assertions.assertTrue(Objects.requireNonNull(response.getBody()).getErrorsFields().containsKey("email")),
                () -> Assertions.assertTrue(Objects.requireNonNull(response.getBody()).getErrorsFields().containsKey("document")),
                () -> Assertions.assertTrue(Objects.requireNonNull(response.getBody()).getErrorsFields().containsValue("Email is not valid")),
                () -> Assertions.assertTrue(Objects.requireNonNull(response.getBody()).getErrorsFields().containsValue("Invalid document"))
        );
    }

    /**
     * Checks if the API will not save a duplicated costumer
     *
     * @throws IOException if there's an error on json parse
     */
    @Test
    @DataSet(value = "/started/customer.yml")
    void shouldNotCreateDuplicatedCustomer() throws IOException {
        CustomerRequest customerRequest = toEntity(jsonPath + "customer.json", CustomerRequest.class);

        var response = restTemplate.
                postForEntity(getPath(), customerRequest, ErrorResponse.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode()),
                () -> Assertions.assertTrue
                        (Objects.requireNonNull(response.getBody()).getMessage().contains("Unique index or primary key violation"))
        );
    }
}
