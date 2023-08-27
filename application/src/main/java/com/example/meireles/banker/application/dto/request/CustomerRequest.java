package com.example.meireles.banker.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Schema(description =  "Customer born date", pattern = "dd/MM/yyyy", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate bornDate;

    @NotNull
    @Schema(description = "Customer email", pattern = "", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

}
