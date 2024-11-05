package com.dev.estacio.finance.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record UserLoginResponse(
        @JsonProperty("id")
        Long id
) {}