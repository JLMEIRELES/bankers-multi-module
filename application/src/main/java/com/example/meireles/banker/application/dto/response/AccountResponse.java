package com.example.meireles.banker.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {

    @NotNull
    @Schema(description = "Number of the created account")
    private String number;

    @NotNull
    @Schema(description = "Digit of the created account")
    private String digit;

}
