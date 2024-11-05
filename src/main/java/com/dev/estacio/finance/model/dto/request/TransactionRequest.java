package com.dev.estacio.finance.model.dto.request;

import com.dev.estacio.finance.model.enums.AccountType;
import com.dev.estacio.finance.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TransactionRequest(
        @JsonProperty("descricao")
        @NotNull(message = "transaction.description.not-null")
        String description,

        @JsonProperty("data")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate date,

        @JsonProperty("valor")
        @NotNull(message = "transaction.amount.not-null")
        Double amount,

        @JsonProperty("pago")
        Boolean paid,

        @JsonProperty("tipoConta")
        @NotNull(message = "transaction.account-type.not-null")
        AccountType accountType,

        @JsonProperty("categoria")
        @NotNull(message = "transaction.category.not-null")
        Category category,

        @JsonProperty("idUsuario")
        @NotNull(message = "transaction.user-id.not-null")
        Long userId
) {
}
