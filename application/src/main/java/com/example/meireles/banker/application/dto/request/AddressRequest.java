package com.example.meireles.banker.application.dto.request;

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
@Schema(description = "An object of address request")
public class AddressRequest {

    @NotNull(message = "Is necessary to inform the address zip code")
    @Schema(description = "Address zipCode", requiredMode = Schema.RequiredMode.REQUIRED)
    private String zipCode;

    @Schema(description = "Address street")
    private String street;

    @Schema(description = "Address complement")
    private String complement;

    @Schema(description = "Address neighborhood")
    private String neighborhood;

    @Schema(description = "Address city")
    private String city;

    @Schema(description = "Address state")
    private String state;
}
