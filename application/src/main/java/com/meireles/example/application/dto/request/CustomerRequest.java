package com.meireles.example.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @NotNull
    @Schema(description = "CPF/CNPJ of customer", requiredMode = Schema.RequiredMode.REQUIRED)
    private String document;

    @NotNull
    @Schema(description = "Customer name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull
    @Schema(description =  "Customer born date", pattern = "", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate bornDate;

    @NotNull
    @Schema(description = "Customer email", pattern = "", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

}
