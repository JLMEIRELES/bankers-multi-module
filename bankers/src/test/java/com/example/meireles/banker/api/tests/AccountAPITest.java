package com.example.meireles.banker.api.tests;

import com.example.meireles.banker.application.dto.request.AccountRequest;
import com.example.meireles.banker.application.dto.response.AccountResponse;
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
class AccountAPITest extends BaseAPITest {

    @InjectMocks
    private ObjectMapper objectMapper;

    private final String resourcesPath = "src/test/resources/";

    @BeforeEach
    void init(){
        basePath = "http://localhost:%d/v1/account";
    }

    public AccountAPITest(){
        super(new ObjectMapper().registerModule(new JavaTimeModule()));
    }

    /**
     * Checks if the account will be created
     *
     * @throws IOException if there's an error on json parse
     */
    @Test
    @ExpectedDataSet(value = "expected/new_account_without_customer.yml")
    void shouldCreateAccount() throws IOException {
        AccountRequest accountRequest =
                toEntity(resourcesPath + "/json/new_account.json", AccountRequest.class);

        var response = restTemplate.
                postForEntity(getPath(), accountRequest, AccountResponse.class);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> Assertions.assertNotNull(Objects.requireNonNull(response.getBody()).getNumber()),
                () -> Assertions.assertNotNull(Objects.requireNonNull(response.getBody()).getDigit())
        );
    }

    /**
     * Checks if the customer of account will be updated
     *
     * @throws IOException if there's an error on json parse
     */
    @Test
    @DataSet(value = "started/account_and_customer.yml", skipCleaningFor = "flyway_schema_history")
    @ExpectedDataSet(value = "expected/change_customer_and_create_new_account.yml")
    void shouldUpdateCustomer() throws IOException {
        AccountRequest accountRequest =
                toEntity(resourcesPath + "/json/new_account.json", AccountRequest.class);

        var response = restTemplate.
                postForEntity(getPath(), accountRequest, AccountResponse.class);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> Assertions.assertNotNull(Objects.requireNonNull(response.getBody()).getNumber()),
                () -> Assertions.assertNotNull(Objects.requireNonNull(response.getBody()).getDigit())
        );
    }
}
