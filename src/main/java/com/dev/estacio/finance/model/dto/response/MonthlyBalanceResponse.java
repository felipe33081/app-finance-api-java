package com.dev.estacio.finance.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record MonthlyBalanceResponse(
    @JsonProperty("totalBalance")
    Double totalBalance,

    @JsonProperty("incomeValue")
    Double incomeValue,

    @JsonProperty("expensesValue")
    Double expensesValue
) {}
