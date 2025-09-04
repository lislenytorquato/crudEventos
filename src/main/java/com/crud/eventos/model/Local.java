package com.crud.eventos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private Long id;
    private String nome;
    private String endereco;
    private int capacidade;
    @OneToOne(mappedBy = "local")
    private Evento evento;
}
