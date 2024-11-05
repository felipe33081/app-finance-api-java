package com.dev.estacio.finance.model;

import com.dev.estacio.finance.model.enums.AccountType;
import com.dev.estacio.finance.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "transaction_data", schema = "public")
public class Transaction extends BaseEntity<Long> {

    @JsonProperty("descricao")
    @Column(name = "description", nullable = false)
    private String description;

    @JsonProperty("data")
    @Column(name = "date")
    private LocalDate date;

    @JsonProperty("valor")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @JsonProperty("pago")
    @Column(name = "paid")
    private Boolean paid;

    @Enumerated(ORDINAL)
    @JsonProperty("tipoConta")
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Enumerated(ORDINAL)
    @JsonProperty("categoria")
    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JsonProperty("usuario")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
