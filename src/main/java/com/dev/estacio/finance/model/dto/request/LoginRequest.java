package com.dev.estacio.finance.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginRequest(
        @JsonProperty("email")
        @NotNull(message = "user.email.not-null")
        String email,

        @JsonProperty("senha")
        @NotNull(message = "user.password.not-null")
        String password
) {}
