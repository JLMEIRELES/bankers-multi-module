package com.example.meireles.banker.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "An object of login request")
public class AuthenticationRequest {
    @NotNull
    private String login;
    @NotNull
    private String password;
}