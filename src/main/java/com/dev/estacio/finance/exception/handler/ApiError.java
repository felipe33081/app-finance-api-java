package com.dev.estacio.finance.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.time.LocalDateTime.now;

@Getter
@Builder
@JsonInclude(NON_NULL)
public class ApiError {

    @JsonProperty("horario")
    @JsonFormat(shape = STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime timestamp = now();

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("detalhe")
    private String detail;

    @JsonProperty("campos")
    private List<Field> fields;

    @Getter
    @Builder
    public static class Field {
        @JsonProperty("nome")
        private String name;

        @JsonProperty("mensagem")
        private String message;
    }

}
