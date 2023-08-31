package com.example.meireles.banker.application.dto.request;

import com.example.meireles.banker.application.dto.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {

    @NotNull(message = "Is necessary to inform the account type")
    @Schema(description = "account type", requiredMode = Schema.RequiredMode.REQUIRED)
    private AccountType accountType;

    @NotNull(message = "Is necessary to inform the account owner")
    @JsonProperty("customer")
    @Valid
    @Schema(description = "account customer", requiredMode = Schema.RequiredMode.REQUIRED)
    private CustomerRequest customerRequest;

}
