package com.crud.eventos.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private Long id;
    private String nome;
    private String email;
    @OneToMany(mappedBy = "participante", cascade = CascadeType.ALL)
    private List<EventoParticipante> eventosParticipantes = new ArrayList<>();
}
