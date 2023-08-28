package com.example.meireles.banker.application.dto.request;

import com.example.meireles.banker.application.utils.validations.annotation.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "An object of customer request")
public class CustomerRequest {

    @NotNull(message = "document cannot be null")
    @Schema(description = "CPF/CNPJ of customer", requiredMode = Schema.RequiredMode.REQUIRED)
    @Document
    private String document;

    @NotNull(message = "name cannot be null")
    @Schema(description = "Customer name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "date cannot be null")
    @Schema(description =  "Customer born date", pattern = "dd/MM/yyyy", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate bornDate;

    @NotNull(message = "email cannot be null")
    @Schema(description = "Customer email", pattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\"\n" +
            "            + \"[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email(message = "Email is not valid", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;

}
