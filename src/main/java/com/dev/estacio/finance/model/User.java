package com.dev.estacio.finance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_data", schema = "public")
public class User extends BaseEntity<Long> {

    @JsonProperty("nome")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonProperty("email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonProperty("senha")
    @Column(name = "password", nullable = false)
    private String password;

}
