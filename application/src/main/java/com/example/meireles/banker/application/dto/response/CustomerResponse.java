package com.example.meireles.banker.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CustomerResponse {

    @NotNull
    @Schema(description = "Id of created customer")
    private Long id;

}
