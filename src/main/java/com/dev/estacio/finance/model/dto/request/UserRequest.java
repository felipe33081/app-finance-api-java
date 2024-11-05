package com.dev.estacio.finance.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserRequest(
        @JsonProperty("nome")
        @NotNull(message = "user.name.not-null")
        String name,

        @JsonProperty("email")
        @Email(message = "user.email.valid")
        @NotNull(message = "user.email.not-null")
        String email,

        @JsonProperty("senha")
        @NotNull(message = "user.password.not-null")
        String password
) {}
