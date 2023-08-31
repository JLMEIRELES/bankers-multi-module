package com.example.meireles.banker.application.dto.request;

import com.example.meireles.banker.application.dto.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private AccountType accountType;

    @NotNull(message = "Is necessary to inform the account owner")
    @JsonProperty("customer")
    private CustomerRequest customerRequest;

}
