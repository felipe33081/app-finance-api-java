package com.dev.estacio.finance.model.dto.response;

import com.dev.estacio.finance.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ExpensesByCategoryResponse(
    @JsonProperty("category")
    Category category,

    @JsonProperty("amount")
    Double amount
) {}
