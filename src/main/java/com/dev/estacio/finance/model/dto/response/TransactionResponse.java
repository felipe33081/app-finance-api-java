package com.dev.estacio.finance.model.dto.response;

import com.dev.estacio.finance.model.enums.AccountType;
import com.dev.estacio.finance.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Builder
public record TransactionResponse(
        @JsonProperty("id")
        Long id,

        @JsonProperty("descricao")
        String description,

        @JsonProperty("data")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate date,

        @JsonProperty("valor")
        Double amount,

        @JsonProperty("pago")
        Boolean paid,

        @JsonProperty("tipoConta")
        AccountType accountType,

        @JsonProperty("categoria")
        Category category,

        @JsonProperty("idUsuario")
        Long userId
) {}