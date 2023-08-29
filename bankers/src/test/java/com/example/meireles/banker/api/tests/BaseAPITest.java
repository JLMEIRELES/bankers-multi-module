package com.example.meireles.banker.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RequiredArgsConstructor
public abstract class BaseAPITest {

    protected String basePath;

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected final ObjectMapper objectMapper;

    /**
     * Map a json to an object
     *
     * @param jsonPath the path of the json to be mapped
     * @param object the target class
     * @return the mapped object
     * @param <T> generics
     * @throws IOException if there's an error on json parse
     */
    protected <T> T toEntity(String jsonPath, Class<T> object) throws IOException {
        File file = new File(jsonPath);
        return objectMapper.readValue(file, object);
    }

    /**
     * Get the path of requisition
     *
     * @return the path of the requisition to be done
     */
    protected String getPath() {
        return String.format(basePath, port);
    }

}
