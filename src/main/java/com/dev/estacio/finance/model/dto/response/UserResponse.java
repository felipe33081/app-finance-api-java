package com.dev.estacio.finance.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record UserResponse(
        @JsonProperty("id")
        Long id,

        @JsonProperty("nome")
        String name,

        @JsonProperty("email")
        String email,

        @JsonProperty("senha")
        String password
) {}
