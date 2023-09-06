package com.example.meireles.banker.application.controller.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Class that generates the return body of web exceptions.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private Map<String, String> errorsFields;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;

    private String message;

    /**
     * Constructor content Status and errors fields, for jakarta validations
     *
     * @param status the http status
     * @param errorsField a collection of the errors and its fields
     */
    public ErrorResponse(HttpStatus status, Map<String, String> errorsField) {
        this.errorsFields = errorsField;
        this.status = status.value();
        this.timestamp = LocalDateTime.now();
        this.message = errorsField.toString();
    }

    /**
     * Constructor content status and error message
     *
     * @param status the http status
     * @param message the error message
     */
    public ErrorResponse(HttpStatus status, String message) {
        this.message = message;
        this.status = status.value();
        this.timestamp = LocalDateTime.now();
    }
}
